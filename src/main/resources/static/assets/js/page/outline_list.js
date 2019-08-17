(function() {
    var subjectId = getQueryString('id');
    var model = new Model({
        el: 'body',
        init: {
            open: true
        },
        remote: {
            initLoad: {
                path: 'outline/getOutlines',
                params: {
                    subjectId: subjectId,
                    level: 0
                }
            },
            addOutline: {
                path: 'outline/addOutline',
                method: 'post',
                params: {
                    subjectId: subjectId,
                    parentId: 0
                }
            },
            delOutline: {
                path: 'outline/delOutline',
                method: 'post',
                params: {
                    id: ''
                }
            },
            loadDetail: {
                path: 'outline/getOutlines',
                params: {
                    subjectId: subjectId
                }
            }
        },
        events: {
            mainSelect: function(event) {
                var $el = this.$(event.target);
                if ('SPAN' == event.target.nodeName) {
                    $el = $el.parent();
                }
                $el.addClass('active').siblings().removeClass('active');
                this.loadDetail();
            },

            subSelect: function(event) {
                var $el = this.$(event.target);
                if ('DIV' == event.target.nodeName) {
                    $el = $el.parent();
                }
                $el.addClass('active').siblings().removeClass('active');
            },

            addParentClick: function(event) {
                var type = this.addParentStatus;
                var sort = null;
                if ('addParent' == type) {
                    sort = parseInt(this.mainContainer.children(':last').data('sort')) + 1;
                } else if ('insertParent' == type) {
                    sort = parseInt(this.mainContainer.children('.active').data('sort'));
                }
                if (isNaN(sort)) {
                    sort = 1;
                }
                var title = this.parentName.val();
                var description = this.parentDescription.val();
                this.comp('remote', ['addOutline', function(config) {
                    config.params.sort = sort;
                    config.params.title = title;
                    config.params.description = description;
                }]);
                this.parentName.val('');
                this.parentDescription.val('');
            },

            cancelParentClick: function(event) {
                this.parentName.val('');
                this.parentDescription.val('');
            },

            delParentClick: function(event) {
                this.comp('remote', ['delOutline', function(config) {
                    config.params.id = this.mainContainer.children('.active').data('id');
                }]);
            },

            addChildClick: function(event) {
                var type = this.addChildStatus;
                var sort = null;
                if ('addChild' == type) {
                    sort = parseInt(this.subContainer.children(':last').data('sort')) + 1;
                } else if ('insertChild' == type) {
                    sort = parseInt(this.subContainer.children('.active').data('sort'));
                }
                if (isNaN(sort)) {
                    sort = 1;
                }
                var title = this.childName.val();
                var description = this.childDescription.val();
                var parent = this.mainContainer.children('.active').data('id');
                this.comp('remote', ['addOutline', function(config) {
                    config.params.parentId = parent;
                    config.params.sort = sort;
                    config.params.title = title;
                    config.params.description = description;
                }]);
                this.childName.val('');
                this.childDescription.val('');
            },

            cancelChildClick: function(event) {
                this.childName.val('');
                this.childDescription.val('');
            },

            delChildClick: function(event) {
                this.comp('remote', ['delOutline', function(config) {
                    config.params.id = this.subContainer.children('.active').data('id');
                }]);
            }
        },
        include: {
            elements: {
                '#parentName': 'parentName',
                '#parentDescription': 'parentDescription',
                'div[data-container="list"]': 'mainContainer',
                '#childName': 'childName',
                '#childDescription': 'childDescription',
                'div[data-child="detail"]': 'subContainer'
            },

            events: {
                'click->a[data-target="#addParentWin"]': 'openParentWinClick',
                'click->a[data-target="#addChildWin"]': 'openChildWinClick'
            },

            addParentStatus: '',
            addChildStatus: '',

            detailClone: null,

            openParentWinClick: function(event) {
                this.addParentStatus = this.$(event.target).data('type');
            },

            openChildWinClick: function(event) {
                this.addChildStatus = this.$(event.target).data('type');
            },

            initRenderFilter: function(fieldName, value, $el, idx, obj) {
                if (-1 != fieldName.indexOf('title')) {
                    $el.parent().attr('data-id', obj[idx].id);
                    $el.parent().attr('data-sort', obj[idx].sort);
                }
                return value;
            },

            initRenderComplete: function(data) {
                this.mainContainer.children(':first').trigger('click');
                this.detailClone = this.subContainer.children('a').clone();
                return data;
            },

            loadDetail: function() {
                this.comp('remote', ['loadDetail', function(config) {
                    config.params.level = this.mainContainer.children('.active').data('id');
                }]);
            },

            addOutlineResult: function(result) {
                if (result.status) {
                    alert('添加成功！');
                    this.comp('remote', ['initLoad']);
                    this.comp('remote', ['loadDetail']);
                } else {
                    alert('添加失败！\n' + result.msg);
                }
            },

            delOutlineResult: function(result) {
                if (result.status) {
                    alert('删除成功！');x
                    this.comp('remote', ['initLoad']);
                    this.comp('remote', ['loadDetail']);
                } else {
                    alert('删除失败！\n' + result.msg);
                }
            },

            loadDetailResult: function(result) {
                if (result.status) {
                    this.subContainer.empty();
                    var $el = null;
                    var data = result.data;
                    if (null != data) {
                        if (null != data.list) {
                            for (var i in data.list) {
                                $el = this.detailClone.clone();
                                $el.attr('data-id', data.list[i].id);
                                $el.attr('data-sort', data.list[i].sort);
                                $el.find('h5').text(data.list[i].title);
                                $el.find('p').text(data.list[i].description);
                                this.subContainer.append($el);
                            }
                        }
                    }
                    this.subContainer.children(':first').trigger('click');
                } else {
                    console.err('大纲加载失败！\n' + result.msg);
                }
            }
        }
    });
})();