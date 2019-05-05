(function() {
    var Model = this.Model = function(config) {
        var ctrlCfg = {
            elements: {
                '*[data-field]': '$fields',
                '*[data-container]': '$containers'
            },

            events: {},

            config: {},

            // 配置
            _defCfg : {
                el: 'body',
                fields: {},
                defEl: {},
                init: {
                    open: false,
                    path: '',
                    params: {}
                },
                remote: {},
                events: {}
            },

            // 字段
            _fields: {},

            // 数组行克隆
            _rowClone: {},

            // 初始化数据
            _initData: {},

            /**
             * 准备工作
             */
            prepare: function() {
                var $container = $(config && config.el ? config.el : this._defCfg.el);
                var evetName = null, split = null, event = null, methodName;
                var that = this;
                $('*[data-operate]', $container).each(function(idx, el) {
                    evetName = $(el).data('operate');
                    if (evetName) {
                        split = evetName.split('-');
                        if (2 == split.length) {
                            event = config.events && config.events[split[1]];
                            if (!event) {
                                event = that.eventTmpLib(split[1]);
                            }

                            if (event) {
                                methodName = that.getEventName(split[1])
                                that.events[split[0] + '->*[data-operate="' + evetName + '"]'] = methodName;
                                that[methodName] = event;
                            }
                        }
                    }
                });
            },

            load: function() {
                this._loadFields();
                this._loadRowClone();
                this._defCfg = $.extend(true, this._defCfg, config);
                this.config = $.extend(true, this.config, config.remote);
                this.busPrepare && this.busPrepare();
                this._loadCfgData();
                this._loadInitData();
            },

            /**
             * 加载字段信息
             * @private
             */
            _loadFields: function() {
                // 自动获取字段
                this.$fields.each(this.proxy(function(idx, val) {
                    this._fields = this._defCfg.fields = $.extend(true, this._defCfg.fields, this.parseFieldName($(val).data('field')));
                }));
            },

            /**
             * 加载行克隆
             * @private
             */
            _loadRowClone: function() {
                var $el = null, arrName = null;
                this.$containers.each(this.proxy(function(idx, el) {
                    $el = this.$(el);
                    arrName = $el.data('container');
                    this._rowClone[arrName] = $el.children('*[data-row="' + arrName + '"]').clone();
                }));
            },

            /**
             * 配置初始化数据
             * @private
             */
            _loadCfgData: function() {
                if ('{}' == JSON.stringify(this._defCfg.defEl)) {
                    return ;
                }
                var $el = null, $clone = null, $tmp = null, $tmp1 = null, arrName = null, elName = null, value = null;
                // 渲染对象元素
                this.$fields.each(this.proxy(function(idx, el) {
                    $el = this.$(el);
                    elName = $el.data('field');
                    value = this.parseFieldValue(this._defCfg.defEl, elName);
                    this.comp('domRenderRole', [false, $el, this.initRenderFilter(elName, value, $el, null, this._defCfg.defEl), this.initRenderAttr(elName)]);
                }));
                // 渲染数组元素
                this.$containers.each(this.proxy(function(idx, el) {
                    $el = this.$(el);
                    arrName = $el.data('container');
                    $clone = this._rowClone[arrName].clone();

                    $el.empty();
                    var data = this.parseFieldValue(this._defCfg.defEl, arrName);
                    if (data) {
                        if (data instanceof Array) {
                            data.forEach(this.proxy(function(val, idx, arr) {
                                $tmp = $clone.clone();
                                $tmp.find('*[data-el]').each(this.proxy(function(i, el) {
                                    $tmp1 = this.$(el);
                                    elName = $tmp1.data('el');
                                    if ('_idx' == elName) {
                                        this.comp('domRenderRole', [false, $tmp1,
                                            this.initRenderFilter(arrName + '[' + idx + '].' + '_idx', idx, $tmp1, idx, this._initData),
                                            this.initRenderAttr(arrName + '[' + idx + '].' + elName)]);
                                    } else {
                                        // 判断元素是对象还是基本类型
                                        if ('object' == typeof val) {
                                            this.comp('domRenderRole', [false, $tmp1, this.parseFieldValue(val, elName), this.initRenderAttr(elName)]);
                                        } else {
                                            if ('_val' == elName) {
                                                this.comp('domRenderRole', [false, $tmp1,
                                                    this.initRenderFilter(arrName + '[' + idx + '].' + elName, val, $tmp1, idx, this._initData),
                                                    this.initRenderAttr(arrName + '[' + idx + '].' + elName)]);
                                            }
                                        }
                                    }
                                }));
                                $el.append($tmp);
                            }));
                        } else {
                            $tmp = $clone.clone();
                            $tmp.find('*[data-el]').each(this.proxy(function(idx, el) {
                                elName = this.$(el).data('el');
                                this.comp('domRenderRole', [false, this.$(el), this.parseFieldValue(data, elName), this.initRenderAttr(elName)]);
                            }));
                            $el.append($tmp);
                        }
                    }
                }));
                this.initRenderComplete && this.initRenderComplete(this._initData);
            },

            /**
             * 获取后台初始化数据
             * @private
             */
            _loadInitData: function() {
                if (this._defCfg.init.open) {
                    this.comp('remote', ['initLoad', this._defCfg.init.before]);
                }
            },

            /**
             * 初始化渲染过滤器
             * @param fieldName 字段名
             * @param value 值
             * @param $el dom元素
             * @param idx 数组下标，对象则是null
             * @param obj 数据对象
             * @returns {*}
             */
            initRenderFilter: function(fieldName, value, $el, idx, obj) {
                return value;
            },

            /**
             * 初始化渲染属性
             * @param fieldName 字段名
             * @returns {boolean}
             */
            initRenderAttr: function(fieldName) {
                return false;
            },

            /**
             * 后台初始化数据结果
             * @param result
             */
            initLoadResult: function(result) {
                if (1 == result.status) {
                    this._initData = result.data;
                    var $el = null, $clone = null, $tmp = null, $tmp1 = null, arrName = null, elName = null, value = null;
                    if (this.initLoadAfter) {
                        this._initData = this.initLoadAfter(result.data);
                    }
                    // 渲染对象元素
                    this.$fields.each(this.proxy(function(idx, el) {
                        $el = this.$(el);
                        elName = $el.data('field');
                        value = this.parseFieldValue(this._initData, elName);
                        this.comp('domRenderRole', [false, $el, this.initRenderFilter(elName, value, $el, null, this._initData), this.initRenderAttr(elName)]);
                    }));
                    // 渲染数组元素
                    this.$containers.each(this.proxy(function(idx, el) {
                        $el = this.$(el);
                        arrName = $el.data('container');
                        $clone = this._rowClone[arrName].clone();

                        $el.empty();
                        var data = this.parseFieldValue(this._initData, arrName);
                        if (data) {
                            if (data instanceof Array) {
                                data.forEach(this.proxy(function(val, idx, arr) {
                                    $tmp = $clone.clone();
                                    $tmp.find('*[data-el]').each(this.proxy(function(i, el) {
                                        $tmp1 = this.$(el);
                                        elName = $tmp1.data('el');
                                        if ('_idx' == elName) {
                                            this.comp('domRenderRole', [false, $tmp1, this.initRenderFilter(arrName + '[' + idx + '].' + '_idx', idx, $tmp1, idx, this._initData[arrName]), this.initRenderAttr(elName)]);
                                        } else {
                                            // 判断元素是对象还是基本类型
                                            if ('object' == typeof val) {
                                                this.comp('domRenderRole', [false, $tmp1,
                                                    this.initRenderFilter(arrName + '[' + idx + '].' + elName, this.parseFieldValue(val, elName), $tmp1, idx, this._initData[arrName]), this.initRenderAttr(elName)]);
                                            } else {
                                                if ('_val' == elName) {
                                                    this.comp('domRenderRole', [false, $tmp1,
                                                        this.initRenderFilter(arrName + '[' + idx + '].' + elName, val, $tmp1, idx, this._initData), this.initRenderAttr(elName)]);
                                                }
                                            }
                                        }
                                    }));

                                    $el.append($tmp);
                                }));
                            } else {
                                $tmp = $clone.clone();
                                $tmp.find('*[data-el]').each(this.proxy(function(idx, el) {
                                    elName = this.$(el).data('el');
                                    this.comp('domRenderRole', [false, this.$(el), this.parseFieldValue(data, elName), this.initRenderAttr(elName)]);
                                }));
                                $el.append($tmp);
                            }
                        }
                    }));
                    if (this.initLoadRenderComplete) {
                        this.initLoadRenderComplete(this._initData);
                    }
                    this.initRenderComplete && this.initRenderComplete(this._initData);
                } else {
                    console.error(result.msg);
                }
            },

            /**
             * 获取事件名
             * @param name
             * @returns {string}
             */
            getEventName: function(name) {
                return 'event_' + name;
            },

            /**
             *
             * @param eventName
             * @returns {*}
             */
            eventTmpLib: function(eventName) {
                if ('submit' == eventName) {
                    return function(event) {
                        var vaildEls = this.$('*[data-vaild]');
                        var chkPass = true;
                        var fieldName = null;

                        vaildEls.each(this.proxy(function(idx, el) {
                            fieldName = this.$(el).data('field');
                            if (!Verify.IsPass(this.$(el))) { // 表单验证
                                chkPass = false;
                                return ;
                            }
                        }));

                        var subData = this.harvest();

                        if (chkPass) {
                            if (this.submitBefore) {
                                subData = this.submitBefore(subData, event);
                            }
                            if (this.subPass(subData, event)) {
                                this.comp('remote', ['submit', function(config) {
                                    config.params = subData;
                                }]);
                            }
                        }
                    };
                } else if ('back' == eventName) {
                    history.back();
                } else {
                    return null;
                }
            },

            /**
             *解析字段名
             * @param name
             * @param init
             * @returns {{}}
             */
            parseFieldName: function(name, init) {
                var obj = {};
                if (!name) {
                    return obj;
                }

                var split = name.split('.');
                if (1 == split.length) {
                    obj[name] = init || null;
                } else {
                    obj[split[0]] = this.parseFieldName(name.substring(name.indexOf('.') + 1, name.length), init);
                }
                return obj;
            },

            /**
             * 解析值
             * @param value
             * @param name
             * @returns {*}
             */
            parseFieldValue: function(value, name) {
                var split = name.split('.');
                if (1 == split.length) {
                    return value[split[0]];
                } else {
                    if (!value[split[0]]) {
                        return ;
                    }
                    return this.parseFieldValue(value[split[0]], name.substring(name.indexOf('.') + 1, name.length));
                }
            },

            /**
             * 获取页面数据
             * @returns {{}}
             */
            harvest: function() {
                var obj = {}, objRow = {};
                var fieldName = null, $el = null, $el1 = null, $el2 = null, arrName = null;
                var str = '';
                // 获取对象元素
                this.$fields.each(this.proxy(function(idx, el) {
                    $el = this.$(el);
                    fieldName = $el.data('field');
                    if ('INPUT' == el.tagName && 'checkbox' == $el.attr('type')) {
                        $el = this.$('*[data-field="' + fieldName + '"]');
                    }
                    $.extend(true, obj, this.parseFieldName(fieldName));
                    str = 'obj.' + fieldName + '="' + this.comp('domRenderRole', [true, $el]) + '"';
                    eval(str);
                }));
                // 获取数组元素
                this.$containers.each(this.proxy(function(idx, el) {
                    $el = this.$(el);
                    arrName = $el.data('container');
                    $.extend(true, obj, this.parseFieldName(arrName, new Array));
                    var arrObj = this.parseFieldValue(obj, arrName);
                    $el.children('*[data-row]').each(this.proxy(function(idx1, el1) {
                        $el1 = this.$(el1);
                        if (0 == $el1.find('*[data-el="_val"]').length) {
                            objRow = {};
                            $el1.find('*[data-el]').each(this.proxy(function(idx2, el2) {
                                $el2 = this.$(el2);
                                fieldName = $el2.data('el');
                                if ('INPUT' == el.tagName && 'checkbox' == $el.attr('type')) {
                                    $el2 = this.$('*[data-el="' + fieldName + '"]');
                                }
                                if ('_idx' != fieldName) {
                                    $.extend(true, objRow, this.parseFieldName(fieldName));
                                    str = 'objRow.' + fieldName + '="' + this.comp('domRenderRole', [true, $el2]) + '"';
                                    eval(str);
                                }
                            }));
                            arrObj.push(objRow);
                        } else {
                            arrObj.push(this.comp('domRenderRole', [true, $el1.find('*[data-el="_val"]')]));
                        }
                    }));
                }));
                return obj;
            },

            /**
             * 提交通过
             */
            subPass: function(subData, event) {
                return true;
            }
        };

        ctrlCfg.prepare();

        var sepa = org.eocencle.sepa;
        var Ctrl = new sepa.Class([sepa.Controller, sepa.CRemote, sepa.CDomRenderRole]);
        Ctrl.include(ctrlCfg);
        config.include && Ctrl.include(config.include);
        config.extend && Ctrl.extend(config.extend);

        return new Ctrl(config && config.el ? config.el : this._defCfg.el);
    };

})();