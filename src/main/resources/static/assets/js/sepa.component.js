/**
 * Sepa.Component
 *
 * Version: 3.0.0
 * Author:  huanStephen
 * License: MIT
 * Date:    2017-4-27
 * Update:  2017-10-30
 */
(function() {
    var sepa = org.eocencle.sepa;

    var _CDomRenderRole = sepa.CDomRenderRole = new sepa.Class;
    /**
     * Dom映射规则
     * isRead：true为读取，false为写入
     * $el：jquery元素
     * value：写入值
     * attr：读写属性
     */
    _CDomRenderRole.extend({
        _component : {
            _common : {
                domRenderRole : function(isRead, $el, value, attr) {
                    if (!$el || 0 == $el.length) {
                        throw('Invalid element');
                    }

                    if (!isRead && (undefined == value || null == value)) {
                        return ;
                    }

                    var result = '';

                    if ($el.length && 'INPUT' == $el[0].tagName && 'checkbox' == $el.attr('type') && !isRead) {
                        $el.removeAttr('checked');
                    }
                    $el.each(function(idx, el) {
                        var $el = $(el);
                        if (attr) {
                            if (isRead) {
                                if ('html' == attr) {
                                    result += $el.html() + ',';
                                } else {
                                    result += $el.attr(attr) + ',';
                                }
                            } else {
                                if ('html' == attr) {
                                    $el.html(value);
                                } else {
                                    $el.attr(attr, value);
                                }
                            }
                        } else {
                            if ('INPUT' === el.nodeName) {
                                var type = $el.attr('type');
                                if ('radio' === type) {
                                    if (isRead) {
                                        var $radio = $('input[name="' + $el.attr('name') + '"]:checked');
                                        if (0 != $radio.length) {
                                            result = $radio.val();
                                        }
                                    } else {
                                        if ($el.val() == value) {
                                            $el.attr('checked', 'checked');
                                        } else {
                                            $el.removeAttr('checked');
                                        }
                                    }
                                } else if ('checkbox' === type) {
                                    if (isRead) {
                                        result += $el.is(':checked') ? ($el.val() + ',') : '';
                                    } else {
                                        var vals = value.split(',');
                                        vals.forEach(function(val, idx, arr) {
                                            if ($el.val() == val) {
                                                $el.attr('checked', 'checked');
                                            }
                                        });
                                    }
                                } else {
                                    if (isRead) {
                                        result += $el.val() + ',';
                                    } else {
                                        $el.val(value);
                                    }
                                }
                            } else if ('TEXTAREA' === el.nodeName || 'SELECT' === el.nodeName) {
                                if (isRead) {
                                    result += $el.val() + ',';
                                } else {
                                    $el.val(value);
                                }
                            } else {
                                if (isRead) {
                                    result += $el.text() + ',';
                                } else {
                                    $el.text(value);
                                }
                            }
                        }
                    });

                    if (isRead) {
                        if ('' !== result && ',' == result.charAt(result.length - 1)) {
                            result = result.substring(0, result.length - 1);
                        }
                        return result;
                    }
                }
            }
        }
    });

    var _CCombVaildate = sepa.CCombVaildate = new sepa.Class;
    /**
     * 组合验证
     * model：数据模型，验证完毕后填充的数据
     * vailds：验证规则
     * errMsgFun：错误处理函数
     */
    _CCombVaildate.extend({
        _component : {
            _common : {
                combVaildate : function(model, vailds, errMsgFun) {
                    var attrs = model.attributes();

                    var result = true, msg = '';
                    for (var name in attrs) {
                        if (!name || !this[name]) {
                            continue;
                        }

                        var val = this.comp('domRenderRole', [true, this[name]]);

                        if (vailds[name]) {
                            var vs = $.extend(true, {}, vailds[name]);

                            for (var idx in vs) {
                                if(msg) break;

                                vs[idx].push(val);
                                var errMsg = this.comp('vaildate', vs[idx]);
                                msg += errMsg;
                                errMsgFun.call(this[name], errMsg);
                            }

                            if (!msg) {
                                model[name] = val;
                            }
                            result = result && !msg;
                            msg = '';
                        } else {
                            model[name] = val;
                        }
                    }
                    return result;
                }
            }
        }
    });

    var _CPage = sepa.CPage = new sepa.Class;
    /**
     * 分页组件
     * {
     * 	block: //数字块个数，最小为5
     * 	container: //显示容器,jquery选择器
     * 	btnFontPos: //按钮上字体显示位置,jquery选择器
     * 	btns: {
     * 		prevBtn: //表示上一页,blocks方法,必须包含名为prev的类
     * 		nextBtn: //表示下一页,blocks方法,必须包含名为next的类
     * 		actBtn: //表示当前活动页,blocks方法
     * 		pageBtn: //表示数字页,blocks方法,必须包含名为num的类
     * 		moitBtn: //表示点页,blocks方法
     * 	}
     * 	methods: {
     * 		prevMethod: //上一页响应方法
     * 		nextMethod: //下一页响应方法
     * 		pageMethod: //数字页响应方法
     * 	}
     * }
     */
    _CPage.extend({
        _component : {
            _common: {
                openPage : function(cfgName) {
                    var cfg = this.pageCfg = this.config[cfgName];
                    if (!cfg) {
                        throw ReferenceError('Configuration of #' + cfgName + ' is not found!');
                    }
                    var $el = $(cfg.container);
                    $el.on('click','.prev', this.proxy(this[cfg.methods.prevMethod]));
                    $el.on('click','.next', this.proxy(this[cfg.methods.nextMethod]));
                    $el.on('click','.num', this.proxy(this[cfg.methods.pageMethod]));
                },
                paginate : function(currPage, totalPage) {
                    var cfg = this.pageCfg;

                    var el = $(cfg.container);

                    var openFontPos = false;
                    var btnFontPos = cfg.btnFontPos;
                    if (btnFontPos) {
                        openFontPos = true;
                    }

                    var prevBtn = this[cfg.btns.prevBtn];
                    var nextBtn = this[cfg.btns.nextBtn];
                    var actBtn = this[cfg.btns.actBtn];
                    var pageBtn = this[cfg.btns.pageBtn];
                    var moitBtn = this[cfg.btns.moitBtn];
                    el.empty();

                    if (cfg.block < 5) {
                        throw('There are at least 5 paging blocks!');
                    }

                    var lr = parseInt(cfg.block / 2);
                    var middle = lr + 1;

                    //前页显示控制
                    if (currPage != 1) {
                        el.append(prevBtn.clone());
                    }
                    if (totalPage <= cfg.block) {
                        var no;
                        for (var i = 1; i <= totalPage; i ++) {
                            if(currPage == i) {
                                no = actBtn.clone();
                            } else {
                                no = pageBtn.clone();
                            }
                            openFontPos ? $(btnFontPos, no).text(i) : no.text(i);
                            el.append(no);
                        }
                    } else {
                        var before = currPage - middle;
                        var after = totalPage - middle;
                        var no;
                        if(before <= 1 && currPage >= after) {
                            for(var i = 1; i <= totalPage; i ++) {
                                if(currPage == i) {
                                    no = actBtn.clone();
                                } else {
                                    no = pageBtn.clone();
                                }
                                openFontPos ? $(btnFontPos, no).text(i) : no.text(i);
                                el.append(no);
                            };
                        }
                        //前面有省略
                        if (before > 1 && currPage >= after) {
                            no = pageBtn.clone();
                            openFontPos ? $(btnFontPos, no).text(1) : no.text(1);
                            el.append(no);
                            if (1 != currPage - cfg.block) {
                                el.append(moitBtn.clone());
                            }
                            var sw = lr + (middle - (totalPage - currPage)) - 1;
                            for (var i = sw; i >= 1; i --) {
                                no = pageBtn.clone();
                                openFontPos ? $(btnFontPos, no).text(currPage - i) : no.text(currPage - i);
                                el.append(no);
                            }
                            no = actBtn.clone();
                            openFontPos ? $(btnFontPos, no).text(currPage) : no.text(currPage);
                            el.append(no);
                            for (var i = currPage + 1; i <= totalPage; i ++) {
                                no = pageBtn.clone();
                                openFontPos ? $(btnFontPos, no).text(i) : no.text(i);
                                el.append(no);
                            };
                        }
                        //后面有省略
                        if (before <= 1 && currPage < after) {
                            for(var i = 1; i <= currPage; i ++) {
                                if(currPage == i) {
                                    no = actBtn.clone();
                                } else {
                                    no = pageBtn.clone();
                                }
                                openFontPos ? $(btnFontPos, no).text(i) : no.text(i);
                                el.append(no);
                            }
                            var sw = lr + (middle - (currPage - 1)) - 1;
                            for (var i = 1; i <= sw; i ++) {
                                no = pageBtn.clone();
                                openFontPos ? $(btnFontPos, no).text(currPage + i) : no.text(currPage + i);
                                el.append(no);
                            }
                            if (cfg.block + 1 != totalPage) {
                                el.append(moitBtn.clone());
                            }
                            no = pageBtn.clone();
                            openFontPos ? $(btnFontPos, no).text(totalPage) : no.text(totalPage);
                            el.append(no);
                        }
                        //前后都有省略
                        if (before > 1 && currPage < after) {
                            no = pageBtn.clone();
                            openFontPos ? $(btnFontPos, no).text(1) : no.text(1);
                            el.append(no);
                            if (1 != currPage - cfg.block) {
                                el.append(moitBtn.clone());
                            }
                            for (var i = lr; i >= 1; i --) {
                                no = pageBtn.clone();
                                openFontPos ? $(btnFontPos, no).text(currPage - i) : no.text(currPage - i);
                                el.append(no);
                            }
                            no = actBtn.clone();
                            openFontPos ? $(btnFontPos, no).text(currPage) : no.text(currPage);
                            el.append(no);
                            for (var i = 1; i <= lr; i ++) {
                                no = pageBtn.clone();
                                openFontPos ? $(btnFontPos, no).text(currPage + i) : no.text(currPage + i);
                                el.append(no);
                            }
                            el.append(moitBtn.clone());
                            no = pageBtn.clone();
                            openFontPos ? $(btnFontPos, no).text(totalPage) : no.text(totalPage);
                            el.append(no);
                        };
                    }
                    //后页显示控制
                    if (totalPage != 0 && currPage != totalPage) {
                        el.append(nextBtn.clone());
                    };
                }
            }
        }
    });
})();