(function() {
    var model = new Model({
        el: 'body',
        init: {
            open: true
        },
        remote: {
            initLoad: {
                path: 'subject/getSubjects',
                params: {
                    currPage: 1,
                    pageSize: 3
                }
            }
        },

        include: {
            initRenderFilter: function(fieldName, value, $el, idx, obj) {
                if (-1 != fieldName.indexOf('id')) {
                    return $el.attr('href') + value;
                }
                return value;
            },

            initRenderAttr: function(fieldName) {
                if ('id' == fieldName) {
                    return 'href';
                }
            },

            initLoadAfter: function(data) {
                if (!this.isLoadPage) {
                    this.page = new Paging;
                    this.page.init({
                        target: '#page',
                        current: data.currPage,
                        pagesize: 3,
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

            initRenderComplete: function(initData) {
                $(".knob").knob();
            }
        }
    });
})();