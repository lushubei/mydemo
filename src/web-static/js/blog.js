/**
 * Created by lushubei on 17/10/1.
 */

//存放所有用户
var blogs = blogs || {};
//操作类型
var operateType = "";
//存放搜索对象
var searchBlogs = searchBlogs| {};
//用户构造方法
var Blog = {
    Create:function(code,title,picture,content,page_view,update_time){
        this.code = code;
        this.title = title;
        this.picture = picture;
        this.content = content;
        this.page_view = page_view;
        this.update_time = update_time;
    },
//添加用户
    addBlogData:function(){
        if(this.code != ""){
            blogs[this.code] = this;
        }
    },
//删除用户
    deleteBlogData:function (){
        for(var i in blogs){
            if(this.code == blogs[i].code){
                delete blogs[i];
            }
        }
    },
//编辑用户
    editBlogData:function(){
        for(var i in blogs){
            if(this.code == blogs[i].code){
                blogs[i].title = this.title;
                blogs[i].content = this.content;
                blogs[i].picture = this.picture;
            }
        }
    },
//查找用户
    findBlogData:function(data){

        for(var i in blogs){
            if(data.code.indexOf(blogs[i].code) >= 0 ||
                data.title.indexOf(blogs[i].title) >= 0){
                searchBlogs[blogs[i].code] = blogs[i];
                refreshDatas(searchBlogs);
            }
        }
    }
};

function New(aClass,aParams){
    function new_(){
        aClass.Create.apply(this,aParams);
    }
    new_.prototype = aClass;
    return new new_();
}


/**
 * 向后台查询所有
 */
function getBlogs(){
    $.ajax({
        type:'get',
        async: false,
        url:"http://127.0.0.1:1111/blog/",
        success: function (datas) {
            blogs={}
            for(var i=0;i<datas.data.length;i++){

                var data =datas.data[i];
                var initBlog = New(Blog,[data.id,data.title,data.picture,data.content,data.page_view,data.update_time]);
                blogs[initBlog.code] = initBlog;
            }

            refreshDatas(blogs);
        },
        error: function(){
            alert('请稍后重试');
        }

    })
}

/**
 * 向后台查询单个博客
 */
function getBlog(id){
    $.ajax({
        type:'get',
        async: false,
        url:"http://127.0.0.1:1111/blog/"+id,
        success: function (datas) {

            var data =datas.data;
            var initBlog = New(Blog,[data.id,data.name,data.picture,data.content]);
            blogs[initBlog.code] = initBlog

            refreshDatas(blogs);
        },
        error: function(){
            alert('请稍后重试');
        }

    })
}

//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg); //匹配目标参数
    if (r != null) return decodeURI(r[2]); return null; //返回参数值…………（有瑕疵，自己改！）
}

function checkEdit(){

    var id = getUrlParam('edit_id');

    if(id){
        $.ajax({
            type:'get',
            async: false,
            url:"http://127.0.0.1:1111/blog/"+id,
            success: function (datas) {

                var data = datas.data;

                //标题
                $("#title").val(data.title);
                //图片链接
                var urlElement = document.getElementById("upload_url");
                var html = "<div id='upload_picture_url'>"+ data.picture +" </div>";
                urlElement.innerHTML =html;

                //内容
                $("#content").val(data.content);

            },
            error: function(){
                alert('请稍后重试');
            }

        })
    }
}

function getBlogDetails(){

    var id = getUrlParam('id');

    if(id){
        $.ajax({
            type:'get',
            async: false,
            url:"http://127.0.0.1:1111/blog/"+id,
            success: function (datas) {


                var data = datas.data;

                //标题
                $("#title").html(data.title);
                //作者
                $("#author_name").html(data.author_name);
                //阅读量
                $("#page_view").html(data.page_view);
                //时间
                $("#update_time").html(data.update_time);

                //图片链接
                var urlElement = document.getElementById("picture_url");
                var html = "<img src='" + data.picture +"' />";
                urlElement.innerHTML =html;

                //内容
                $("#content").html(data.content);


            },
            error: function(){
                alert('请稍后重试');
            }
        })
    }else{
        alert('不行额');
    }
}


/**
 * 向后台添加Blog
 */

function addBlog(title,content,picture,author_id){

    var body = {
        "author_id": author_id,
        "content":content,
        "picture": picture,
        "title": title
    }

    $.ajax({
        type:'post',
        async: false,
        url:"http://localhost:1111/blog/add",
        data:JSON.stringify(body),
        contentType:'application/json;charset=UTF-8',
        success: function (resp) {
            // console.log(resp.);
            // initBlogDatas();
            if(resp.code == "000000"){
                alert("提交成功");
                window.location.href = 'blog.html';
            }
            else{
                alert(resp.msg);
            }
        },
        error: function(){
            alert('请稍后重试');
        }

    })
}

/**
 * 向后台修改Blog
 */
function editBlog(id,title,content,picture,author_id){

    var body = {
        "id":id,
        "author_id": author_id,
        "content":content,
        "picture": picture,
        "title": title
    }

    $.ajax({
        type:'put',
        async: false,
        url:"http://127.0.0.1:1111/blog/",
        data:JSON.stringify(body),
        contentType:'application/json;charset=UTF-8',
        success: function (data) {
            alert("修改成功");
            window.location.href = 'blog.html';
            // initBlogDatas();
        },
        error: function(){
            alert('请稍后重试');
        }

    })
}

/**
 * 向后台删除Blog
 */
function deleteBlog(id){
    $.ajax({
        type:'delete',
        async: false,
        url:"http://127.0.0.1:1111/blog/"+id,
        data:null,
        contentType:'application/json;charset=UTF-8',
        success: function (datas) {
            alert("删除博客成功！");
            blogs={}
            for(var i=0;i<datas.data.length;i++){

                var data =datas.data[i];
                var initBlog = New(Blog,[data.id,data.title,data.picture,data.content,data.page_view,data.update_time]);
                blogs[initBlog.code] = initBlog;
            }

            refreshDatas(blogs);
        },
        error: function(){
            alert('删除失败，请稍后重试');
        }

    })

}

/**
 * 上传文件到后台
 */
function uploadPicture(){
    var formData = new FormData();
    var file = $("input[type='file']")[0].files[0] ;

    formData.append('file',file);
    formData.append('name','xiaobei');
    formData.append('age',30);

    $.ajax({
        type:'post',
        async: false,
        url:"http://localhost:1111/picture/upload",
        data:formData,
        // datatype:'json',
        contentType:false,
        processData: false,
        success: function (ret) {
            alert("上传成功！");
            var urlElement = document.getElementById("upload_url");
            var html = '';
            for(var i = 0;i < ret.data.length;i++){
                var url = ret.data[i];
                html = html + "<div id='upload_picture_url'>"+ url +" </div>";
            }

            urlElement.innerHTML =html;
            // refreshDatas(blogs);
        },
        error: function(ret){
            console.log("又失败喽,看看返回的数据是啥：",ret);
            alert('上传失败，请稍后重试');
        }



    })

}

/**
 * 初始化用户数据
 */
function initBlogDatas(){

    getBlogs();

    // return blogs;
}

//监听增加按钮单击事件
$('.addBlog').on('click', function () {
    var title = $("#title").val();

    var content = $("#content").val();

    var picture = $("#upload_picture_url").html();
    var author_id = 1;

    var edit_id = getUrlParam('edit_id');
    if(edit_id){
        editBlog(edit_id,title,content,picture,author_id);
    }else {
        addBlog(title, content, picture, author_id);
    }
});

//监听删除按钮单击事件
$(".delBlog").live('click',function () {
    var id = $(this).attr("value");
    deleteBlog(id);
});

//监听编辑按钮单击事件
$(".editBlog").live('click',function () {
    var id = $(this).attr("value");

    window.location.href = 'addBlog.html'+'?edit_id='+id;
    // getEditBlog(id);
});

/**
 * 首次加载页面执行方法
 */
function LoadBlogDatas(){
    getBlogs();

}

/**
 * 往表格添加一行html数据
 */
function addRowData(datas){
    var tbodyElement = document.getElementById("tbody");
    var html = "";
    var color = "warning";
    var flag = true;

    for(var i in datas){

        if(flag){
            color = "info";
        }else{
            color = "warning";
        }

        html = html + "<div class='projects-inform wow bounceInLeft' data-wow-delay='0.4s'>" +
            "<h3 id='title'>" +
            "<a href='blogDetails.html?id="+datas[i].code +"'>" + datas[i].title +"</a>" +
            "</h3>" +
            "<div class='project-img col-md-4' id='picture'>" +
              "<img src= " + datas[i].picture +" alt=''/>" +
            "</div>" +
            "<div class='project-text col-md-6' id='time'>时间： "
                + datas[i].update_time +"    浏览量："
                + datas[i].page_view +

                "<button type='button'  class='btn btn-default delBlog' name='delBlog' value="+ datas[i].code + ">删除</button>"
                + "<button type='button'  class='btn btn-default editBlog' name='editBlog' value='"+ datas[i].code + "'>编辑</button>" +
            "</div>" +

            "<div>"
            + datas[i].content
            +"</div>" +
            "<div class='clearfix'></div></div>";

        flag = !flag;//颜色转换
    }
    tbodyElement.innerHTML = html;
}
/**
 * 刷新用户数据
 */
function refreshDatas(datas){
    addRowData(datas);
};

/**
 * 收集一行数据
 */
function collectionRowData(param){
    var tdElement = param.getElementsByTagName("td");
    var blogArr = [];
    for(var i=1;i<tdElement.length;i++){
        var temp = tdElement[i].textContent;
        blogArr[i-1] = temp;
    }
    var blog = New(Blog,blogArr);
    return blog;
}
/**
 * 用户操作方法
 */
function optionBlogData(param){
//获得操作类别
    var optionType = param.getAttribute("id");
    if(optionType == "blog_add"){
        operateType = "add";
    }else if(optionType == "blog_delete"){
        var checkRowData = isCheckedData();
        var blogs = collectionRowData(checkRowData);
        deletePerson(blog);

        // blog.deleteBlogData();
        // refreshDatas(blogs);
    }else if(optionType == "blog_edit"){
        operateType = "edit";
        var checkRowData = isCheckedData();
        var blogs = collectionRowData(checkRowData);
        var modal_body = document.getElementById("modal-body");
        var inputElements= modal_body.getElementsByTagName("input");
        for(var i=0;i<inputElements.length;i++){
            var temp = inputElements[i].id.substring(2,inputElements[i].id.length)
            inputElements[i].value = blog[temp];
        }
    }else if(optionType == "blog_find"){
        var s_code = document.getElementById("s_code").value;
        var s_title = document.getElementById("s_title").value;
        var s_all= document.getElementById("s_all").value;
//搜索数据
        var s_data = s_data || {};
        console.log(s_code);
        s_data.code = s_code;
        if(s_code != ""){
            getPerson(s_code)
        }else{
            s_data.title = s_title;
            s_data.all = s_all;

            var blog = New(Blog,[]);
            blog.findBlogData(s_data);
        }
    }else{

    }
}

/**
 * 是否选中数据,返回选中数据的行
 */
function isCheckedData(){
    var tbodyElement =document.getElementById("tbody");
    var trElements = tbodyElement.getElementsByTagName("tr");
    var flag = false;
    for(var i=0;i<trElements.length;i++){
        var inputElement = trElements[i].getElementsByTagName("input")[0];
        if(inputElement.checked){
            flag = true;
            return trElements[i];
        }
    }
    if(!flag){
        alert("请选择一条记录！");
        $('#myModal').unbind("on");
    }
}

function search(){

    var searchMessage = $("#searchMessage").val();

    console.log("searchMessage:",searchMessage);


    if(searchMessage){
        $.ajax({
            type:'get',
            async: false,
            url:"http://127.0.0.1:1111/blog/search?searchMessage=" + searchMessage,
            success: function (datas) {
                blogs={}
                for(var i=0;i<datas.data.length;i++){

                    var data =datas.data[i];
                    var initBlog = New(Blog,[data.id,data.title,data.picture,data.content,data.page_view,data.update_time]);
                    blogs[initBlog.code] = initBlog;
                }

                refreshDatas(blogs);
            },
            error: function(){
                alert('请稍后重试');
            }

        })
    }

}

paginationFunc('pagination', 1, 5, function (page) {

    if(page){
        $.ajax({
            type:'get',
            async: false,
            url:"http://127.0.0.1:1111/blog/?page=" + page,
            success: function (datas) {
                blogs={}
                for(var i=0;i<datas.data.length;i++){

                    var data =datas.data[i];
                    var initBlog = New(Blog,[data.id,data.title,data.picture,data.content,data.page_view,data.update_time]);
                    blogs[initBlog.code] = initBlog;
                }

                refreshDatas(blogs);
            },
            error: function(){
                alert('请稍后重试');
            }

        })
    }

});