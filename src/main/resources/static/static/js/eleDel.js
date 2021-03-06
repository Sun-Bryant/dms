/*
 * @Author: https://github.com/WangEn
 * @Author: https://gitee.com/lovetime/
 * @Date:   2018-03-27
 * @lastModify 2018-3-28
 * +----------------------------------------------------------------------
 * | WeAdmin 表格table中多个删除等操作公用js
 * | 有改用时直接复制到对应页面也不影响使用
 * +----------------------------------------------------------------------
 */
layui.extend({
    admin: '{/}../../static/js/admin'
});
layui.use(['laydate', 'jquery', 'admin', 'table'], function () {
    var laydate = layui.laydate,
        layer = layui.layer,
        $ = layui.jquery,
        table = layui.table,
        form = layui.form;

    admin = layui.admin;
    //执行一个laydate实例
    laydate.render({
        elem: '#start'//指定元素
    });

    //执行一个laydate实例
    laydate.render({
        elem: '#end' //指定元素
    });

    // var checkStatus = table.checkStatus('idTest');
    // console.log(checkStatus.data);


    /*用户-删除*/
    window.member_del = function (obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            //发异步删除数据
            $.ajax({
                url: "/manager/delete",
                data: {
                    id: id
                },
                success: function (result) {
                    if (result > 0) {
                        $(obj).parents("tr").remove();
                        layer.msg('已删除!', {
                            icon: 1,
                            time: 1000
                        });
                        location.replace(location.href);
                    } else {
                        alert("删除错误");
                    }
                },
                error: function () {
                    alert("请求错误");
                }
            });

        });
    }

    /*用户-停用*/
    window.member_stop_breakdown_examine = function (obj, id) {
        layer.confirm('确认要改变状态吗？', function (index) {
            if ($(obj).attr('title') == '暂不审批') {
                $.ajax({
                    url: "/breakdown/updateExamine",
                    data: {
                        id: id,
                        examine: 0
                    },
                    success: function (result) {
                        if (result > 0) {
                            //发异步把用户状态进行更改
                            $(obj).attr('title', '审批')
                            $(obj).find('i').removeClass('layui-icon-download-circle').html('&#xe601;');

                            $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('未审批');
                            layer.msg('已更改状态!', {
                                icon: 5,
                                time: 1000
                            });
                        } else {
                            alert("更新错误!!!");
                        }
                    },
                    error: function () {
                        alert("请求错误");
                    }
                });

            } else {
                $.ajax({
                    url: "/breakdown/updateExamine",
                    data: {
                        id: id,
                        examine: 1
                    },
                    success: function (result) {
                        if (result > 0) {
                            $(obj).attr('title', '暂不审批');
                            // $(obj).find('i').html('&#xe601;');
                            $(obj).find('i').removeClass('layui-icon-download-circle').html('&#xe62f;');

                            $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已审批');
                            layer.msg('已更改状态!', {
                                icon: 5,
                                time: 1000
                            });
                        } else {
                            alert("更新错误");
                        }
                    },
                    error: function () {
                        alert("请求错误");
                    }
                });
            }
        });
    }


    /*用户-停用*/
    window.member_stop_breakdown_status = function (obj, id) {
        layer.confirm('确认要改变处理状态吗？', function (index) {
            if ($(obj).attr('title') == '启用') {
                $.ajax({
                    url: "/breakdown/updateStatus1",
                    data: {
                        id: id,
                        status: 0
                    },
                    success: function (result) {
                        if (result > 0) {
                            //发异步把用户状态进行更改
                            $(obj).attr('title', '停用')
                            $(obj).find('i').removeClass('layui-icon-download-circle').html('&#xe601;');

                            $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('未处理');
                            layer.msg('已改变处理状态!', {
                                icon: 5,
                                time: 1000
                            });
                        } else {
                            alert("更新错误!!!");
                        }
                    },
                    error: function () {
                        alert("请求错误");
                    }
                });

            } else {
                $.ajax({
                    url: "/breakdown/updateStatus1",
                    data: {
                        id: id,
                        status: 1
                    },
                    success: function (result) {
                        if (result > 0) {
                            $(obj).attr('title', '启用');
                            // $(obj).find('i').html('&#xe601;');
                            $(obj).find('i').removeClass('layui-icon-download-circle').html('&#xe62f;');

                            $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已处理');
                            layer.msg('已改变处理状态!', {
                                icon: 5,
                                time: 1000
                            });
                        } else {
                            alert("更新错误");
                        }
                    },
                    error: function () {
                        alert("请求错误");
                    }
                });
            }
        });
    }


    /*用户-停用*/
    window.member_stop_breakdown = function (obj, id) {
        layer.confirm('确认要改变审批状态吗？', function (index) {
            if ($(obj).attr('title') == '启用') {
                $.ajax({
                    url: "/breakdown/updateStatus",
                    data: {
                        id: id,
                        examine: 0
                    },
                    success: function (result) {
                        if (result > 0) {
                            //发异步把用户状态进行更改
                            $(obj).attr('title', '停用')
                            $(obj).find('i').removeClass('layui-icon-download-circle').html('&#xe601;');

                            $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('未审批');
                            layer.msg('已改变审批状态!', {
                                icon: 5,
                                time: 1000
                            });
                        } else {
                            alert("更新错误!!!");
                        }
                    },
                    error: function () {
                        alert("请求错误");
                    }
                });

            } else {
                $.ajax({
                    url: "/breakdown/updateStatus",
                    data: {
                        id: id,
                        examine: 1
                    },
                    success: function (result) {
                        if (result > 0) {
                            $(obj).attr('title', '启用');
                            // $(obj).find('i').html('&#xe601;');
                            $(obj).find('i').removeClass('layui-icon-download-circle').html('&#xe62f;');

                            $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已审批');
                            layer.msg('已改变审批状态!', {
                                icon: 5,
                                time: 1000
                            });
                        } else {
                            alert("更新错误");
                        }
                    },
                    error: function () {
                        alert("请求错误");
                    }
                });
            }
        });
    }

    /*-删除*/
    window.member_del_breakdown = function (obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            //发异步删除数据
            $.ajax({
                url: "/breakdown/delete",
                data: {
                    id: id
                },
                success: function (result) {
                    if (result > 0) {
                        $(obj).parents("tr").remove();
                        layer.msg('已删除!', {
                            icon: 1,
                            time: 1000
                        });
                        location.replace(location.href);
                    } else {
                        alert("删除错误");
                    }
                },
                error: function () {
                    alert("请求错误");
                }
            });

        });
    }

    /*维修人员-删除*/
    window.member_del_serviceman = function (obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            //发异步删除数据
            $.ajax({
                url: "/serviceman/delete",
                data: {
                    id: id
                },
                success: function (result) {
                    if (result > 0) {
                        $(obj).parents("tr").remove();
                        layer.msg('已删除!', {
                            icon: 1,
                            time: 1000
                        });
                        location.replace(location.href);
                    } else {
                        alert("删除错误");
                    }
                },
                error: function () {
                    alert("请求错误");
                }
            });

        });
    }



    /*用户-停用*/
    window.member_stop = function (obj, id) {
        layer.confirm('确认要改变状态吗？', function (index) {

            if ($(obj).attr('title') == '启用') {
                $.ajax({
                    url: "/manager/updateStatus",
                    data: {
                        id: id,
                        status: 0
                    },
                    success: function (result) {
                        if (result > 0) {
                            //发异步把用户状态进行更改
                            $(obj).attr('title', '停用')
                            $(obj).find('i').removeClass('layui-icon-download-circle').html('&#xe601;');

                            $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                            layer.msg('已启用!', {
                                icon: 5,
                                time: 1000
                            });
                        } else {
                            alert("更新错误!!!");
                        }
                    },
                    error: function () {
                        alert("请求错误");
                    }
                });

            } else {
                $.ajax({
                    url: "/manager/updateStatus",
                    data: {
                        id: id,
                        status: 1
                    },
                    success: function (result) {
                        if (result > 0) {
                            $(obj).attr('title', '启用');
                            // $(obj).find('i').html('&#xe601;');
                            $(obj).find('i').removeClass('layui-icon-download-circle').html('&#xe62f;');

                            $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                            layer.msg('已停用!', {
                                icon: 5,
                                time: 1000
                            });
                        } else {
                            alert("更新错误");
                        }
                    },
                    error: function () {
                        alert("请求错误");
                    }
                });
            }
        });
    }



    /*维修人员-停用*/
    window.member_stop_serviceman = function (obj, id) {
        layer.confirm('确认要改变状态吗？', function (index) {

            if ($(obj).attr('title') == '启用') {
                $.ajax({
                    url: "/serviceman/updateStatus",
                    data: {
                        id: id,
                        status: 0
                    },
                    success: function (result) {
                        if (result > 0) {
                            //发异步把用户状态进行更改
                            $(obj).attr('title', '停用')
                            $(obj).find('i').removeClass('layui-icon-download-circle').html('&#xe601;');

                            $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                            layer.msg('已启用!', {
                                icon: 5,
                                time: 1000
                            });
                        } else {
                            alert("更新错误!!!");
                        }
                    },
                    error: function () {
                        alert("请求错误");
                    }
                });

            } else {
                $.ajax({
                    url: "/serviceman/updateStatus",
                    data: {
                        id: id,
                        status: 1
                    },
                    success: function (result) {
                        if (result > 0) {
                            $(obj).attr('title', '启用');
                            // $(obj).find('i').html('&#xe601;');
                            $(obj).find('i').removeClass('layui-icon-download-circle').html('&#xe62f;');

                            $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                            layer.msg('已停用!', {
                                icon: 5,
                                time: 1000
                            });
                        } else {
                            alert("更新错误");
                        }
                    },
                    error: function () {
                        alert("请求错误");
                    }
                });
            }
        });



        /*用户-停用*/
        window.member_stop_student = function (obj, id) {
            layer.confirm('确认要改变状态吗？', function (index) {
                if ($(obj).attr('title') == '启用') {
                    $.ajax({
                        url: "/student/updateStatus",
                        data: {
                            no: id,
                            status: 0
                        },
                        success: function (result) {
                            if (result > 0) {
                                //发异步把用户状态进行更改
                                $(obj).attr('title', '停用')
                                $(obj).find('i').removeClass('layui-icon-download-circle').html('&#xe601;');

                                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                                layer.msg('已启用!', {
                                    icon: 5,
                                    time: 1000
                                });
                            } else {
                                alert("更新错误!!!");
                            }
                        },
                        error: function () {
                            alert("请求错误");
                        }
                    });

                } else {
                    $.ajax({
                        url: "/serviceman/updateStatus",
                        data: {
                            id: id,
                            status: 1
                        },
                        success: function (result) {
                            if (result > 0) {
                                $(obj).attr('title', '启用');
                                // $(obj).find('i').html('&#xe601;');
                                $(obj).find('i').removeClass('layui-icon-download-circle').html('&#xe62f;');

                                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                                layer.msg('已停用!', {
                                    icon: 5,
                                    time: 1000
                                });
                            } else {
                                alert("更新错误");
                            }
                        },
                        error: function () {
                            alert("请求错误");
                        }
                    });
                }
            });
        }



        window.delAll = function (argument) {
            var checkStatus = table.checkStatus('demo')
                , data = checkStatus.data;
            alert(JSON.stringify(data));


            var data = tableCheck.getData();
            layer.confirm('确认要删除吗？' + data, function (index) {
                //捉到所有被选中的，发异步进行删除
                layer.msg('删除成功', {
                    icon: 1
                });
                $(".layui-form-checked").not('.header').parents('tr').remove();
            });
        }
        window.replace_class = function (obj, a) {
            console.log(obj);
            $(obj).addClass('current');
        }
    }
});