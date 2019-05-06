(function() {
    var model = new Model({
        el: 'body',
        init: {
            open: true
        },
        remote: {
            initLoad: {
                path: '../camera/getCameraCoorExtByRegionUuid',
                params: {
                    currPage: 1,
                    pageSize: 10
                }
            },
            loadRegionTree: {
                path: '../region/getRegionTree'
            },
            updateCameraCoordinate: {
                path: '../camera/updateCameraCoordinate',
                params: {
                    cameraUuid: '',
                    coordinateX: '',
                    coordinateY: ''
                }
            }
        },
        events: {
            add: function(event) {
                var $el = this.$(event.target);
                var cameraUuid = $el.data('var');
                layer.open({
                    type: 2,
                    title: '操作区',
                    area:["800px","250px"],
                    content: 'zxjk_jk_dialog?coors=' + $el.parent().prev().text(),
                    btn: ['更新', '关闭'],
                    yes: this.proxy(function(index) {
                        this.index = index;
                        var coor = window['layui-layer-iframe' + index].backCoor();
                        if ('' == coor) {
                            this.comp('remote', ['updateCameraCoordinate', function(config) {
                                config.params.cameraUuid = cameraUuid;
                            }]);
                        } else {
                            var coors = coor.split(',');
                            if (2 == coors.length) {
                                this.comp('remote', ['updateCameraCoordinate', function(config) {
                                    config.params.cameraUuid = cameraUuid;
                                    config.params.coordinateX = coors[0];
                                    config.params.coordinateY = coors[1];
                                }]);
                            } else {
                                layer.msg('坐标格式不正确');
                            }
                        }
                    }),
                    cancel: function(index) {

                    }
                });
            }
        },
        include: {
            elements: {
                '#regionTree': 'regionTree'
            },

            page: null,
            isLoadPage: false,

            index: null,

            ztreeSetting: {
                check: {
                    enable: false
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback : {
                    onClick: callback
                }
            },

            busPrepare: function() {
                this.comp('remote', ['loadRegionTree']);
            },

            loadRegionTreeResult: function(result) {
                if (result.status) {
                    $.fn.zTree.init(this.regionTree, this.ztreeSetting, result.data);
                    this.ztreeSetting.check = {
                        enable: true
                    }
                } else {
                    console.error(result.msg);
                }
            },

            search: function(event, treeId ,treeNode, clickFlag) {
                //if (!treeNode.isParent) {
                this.comp('remote', ['initLoad', function(config) {
                    config.params.regionUuid =  treeNode.ext.regionUuid;
                }]);
                //}
            },

            initRenderFilter: function(fieldName, value, $el, idx, obj) {
                if (-1 != fieldName.indexOf('_idx')) {
                    return value + 1;
                }
                if (-1 != fieldName.indexOf('cameraType')) {
                    if (0 == value) {
                        return '枪机';
                    } else if (1 == value) {
                        return '半球';
                    } else if (2 == value) {
                        return '快球';
                    } else if (3 == value) {
                        return '带云镜枪机';
                    }
                }
                if (-1 != fieldName.indexOf('onLineStatus')) {
                    if (value) {
                        return '在线';
                    } else {
                        return '不在线';
                    }
                }
                if (-1 != fieldName.indexOf('coordinateX')) {
                    if (value) {
                        return value + ',' + obj[idx].coordinateY;
                    }
                }
                return value;
            },

            initRenderAttr: function(fieldName) {
                if ('cameraUuid' == fieldName) {
                    return 'data-var';
                }
            },

            initLoadAfter: function(data) {
                if (!this.isLoadPage) {
                    this.page = new Paging;
                    this.page.init({
                        target: '#page',
                        current: data.currPage,
                        pagesize: 10,
                        count: data.total,
                        callback: this.proxy(function(page, size, count) {
                            this.comp('remote', ['initLoad', function(config) {
                                config.params.currPage = page;
                            }]);
                        })
                    });
                    this.isLoadPage = true;
                } else {
                    this.page.render({
                        current: data.currPage,
                        count: data.total,
                        callback: this.proxy(function(page, size, count) {
                            this.comp('remote', ['initLoad', function(config) {
                                config.params.currPage = page;
                            }]);
                        })
                    });
                }
                return data;
            },

            updateCameraCoordinateResult: function(result) {
                if (result.status) {
                    layer.msg('更新成功！', this.proxy(function() {
                        layer.close(this.index);
                        this.comp('remote', ['initLoad']);
                    }));
                } else {
                    console.error(result.msg);
                }
            }
        }
    });
    function callback(event, treeId ,treeNode, clickFlag) {
        model.search(event, treeId ,treeNode, clickFlag);
    }
})();