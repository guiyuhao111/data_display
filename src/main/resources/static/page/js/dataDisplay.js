$(document).ready(function () {
    layui.use(['table','laydate','form'],function () {
        var tableId="dataTable";
        var tableFilter="dataList";
        var table=layui.table;
        var laydate=layui.laydate;
        var form=layui.form;
        var modalIndex;
        var websiteName=$("#websiteName").text();
        table.render({
            //tableId
            elem:"#[id]".replace('[id]',tableId),
            //数据接口
            url:"/data/getDataList",
            //contentType:"application/json",
            //请求参数
            where:{
                websiteName:websiteName,
            },
            toolbar:'#headToolBar',
            //请求头
            //headers:{},
            //开启分页
            page:true,
            // width:"1500",
            limit:10,
            //表头
            cols: [[
                ,{type:'checkbox'}
                ,{field: 'newsID', title: 'ID', hide:true,width:0}
                ,{field: 'title', title: '标题',width:"12%"}
                ,{field: 'link', title: '链接',width:"12%"}
                ,{field: 'author', title: '作者',width:"12%"}
                ,{field: 'type', title: '类型',width:"12%"}
                ,{field: 'pubTime', title: '发布时间',width:"12%"}
                ,{field: 'createTime', title: '添加时间',hide:true}
                ,{field: 'remark', title: '备注',width:"12%"}
                ,{fixed:"right",align:'center',toolbar:'#inlineTool',title:'操作',width:"12%"}
            ]],
            parseData:function (res) {
                return{
                    "code":res.state==0?1:0,
                    "msg":res.message,
                    "data":res.data.list,
                    "count":res.data.totalCount
                }
            },
            done:function () {
                //数据渲染完的回调

            }
        });
        //行内工具栏的点击事件
        table.on('tool([filter])'.replace('[filter]',tableFilter),function (obj) {
            //获得当前数据
            var data=obj.data;
            //获得当前事件
            var layEvent=obj.event;
            //获得当前行的tr的DOM对象
            var tr=obj.tr;
            if (layEvent=='detail') {

            }else if (layEvent=='edit') {
                showModal("编辑");
                //给modal框赋值
                form.val("dataForm",data);
                form.render();
                $("#dataTable").data("rowID",data.newsID);
            }else if (layEvent=='del'){
                layer.confirm('真的删除行么',function (index) {
                    //向服务端发送删除指令服务端返回正常状态
                    //删除行并更新缓存
                    var url="/data/deleteDataByID";
                    $.get(url,{newsID:data.newsID},function (res) {
                        if (res.state==1){
                            obj.del();
                        } else{
                            layer.msg(res.message);
                        }
                    });
                    //关闭提示框
                    layer.close(index);
                })
            }
        });
        //选中复选框的事件
        table.on('checkbox([filter])'.replace('[filter]',tableFilter),function (res) {
        });
        //监听头工具栏的点击事件
        table.on('toolbar([filter])'.replace('[filter]',tableFilter),function (res) {
            var checkStatus=table.checkStatus(res.config.id);
            switch (res.event){
                case 'add':
                    form.val("dataForm",{
                        title:"",
                        link:"",
                        author:"",
                        type:"转载",
                        pubTime:"",
                        remark:"",
                    });
                    form.render();
                    modalIndex = showModal('添加');
                    $("#dataTable").data("rowID","");
                    break;
                case 'delete':
                    var arr=checkStatus.data;
                    var idList="";
                    for (var i=0;i<arr.length;i++){
                        if (i==(arr.length-1)) {
                            idList=idList+arr[i].newsID;
                        }else{
                            idList=idList+arr[i].newsID+",";
                        }
                    }
                    layer.confirm('真的删除行么',function (index) {
                        //向服务端发送删除指令服务端返回正常状态
                        //删除行并更新缓存
                        var url="/data/deleteDataByIds";
                        $.get(url,{idList:idList},function (res) {
                            if (res.state==1) {
                                var options={
                                    page:{
                                        curr:1
                                    }
                                }
                                table.reload(tableId,options);
                            }else{
                                layer.msg(res.message);
                            }
                        });
                        //关闭提示框
                        layer.close(index);
                    })

                    break;
            }
        });
        laydate.render({
            elem:"#pubTime"
        });
        form.on('submit(commit)',function (data) {
            var url;
            var rowID=$("#dataTable").data("rowID");
            var title;
            if (rowID==""){
                url="/data/addData";
                title="添加";
            }else{
                url="/data/updateData";
                title="修改";
                data.field.newsID=rowID;
            }
            data.field.webSiteName=websiteName;
            $.get(url,data.field,function (res) {
                if (res.state==0){
                    layer.msg(title+"数据出错");
                }else{
                    console.log(modalIndex);
                    layer.close(modalIndex);
                    layer.closeAll();
                    var options={
                        page:{
                            curr:1
                        }
                    }
                    table.reload(tableId,options);
                }
            });
        })
    })
})

function showModal(title) {
    layui.use('layer',function () {
        var layer=layui.layer;
        var index = layer.open({
            type:1,
            area:['700px','500px'],
            title:title,
            shade:0,
            content:$("#dataInput")
        })
        return index;
    })
}