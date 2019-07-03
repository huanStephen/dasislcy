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
                    subjectId: subjectId
                }
            },
            addParent: {
                path: 'outline/addOutline',
                method: 'post',
                params: {
                    subjectId: subjectId,
                    parentId: 0
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
            },

            addParentClick: function(event) {
                var sort = this.mainContainer.children(':last').data('sort');
                var title = this.parentName.val();
                var description = this.parentDescription.val();
                this.comp('remote', ['addParent', function(config) {
                    config.params.sort = parseInt(sort) + 1;
                    config.params.title = title;
                    config.params.description = description;
                }]);
            }
        },
        include: {
            elements: {
                '#parentName': 'parentName',
                '#parentDescription': 'parentDescription',
                'div[data-container="list"]': 'mainContainer'
            },

            initRenderFilter: function(fieldName, value, $el, idx, obj) {
                if (-1 != fieldName.indexOf('title')) {
                    $el.parent().attr('data-sort', obj[idx].sort);
                }
                return value;
            },

            initRenderComplete: function(data) {
                this.mainContainer.children(':first').trigger('click');
                return data;
            },

            addParentResult: function(result) {
                if (result.status) {
                    alert('大纲添加成功！');
                    this.comp('remote', ['initLoad']);
                } else {
                    alert('大纲添加失败！\n' + result.msg);
                }
            }
        }
    });
})();