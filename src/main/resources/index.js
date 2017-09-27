/**
 * Created by lushubei on 17/9/26.
 */

//存放所有用户
var users = users || {};
//操作类型
var operateType = "";
//存放搜索对象
var searchUsers = searchUsers || {};
//用户构造方法
var User = {
    Create:function(code,userName,age,description){
        this.code = code;
        this.userName = userName;
        this.age = age;
        this.description = description;
    },
//添加用户
    addUserData:function(){
        if(this.code != ""){
            users[this.code] = this;
        }
    },
//删除用户
    deleteUserData:function (){
        for(var i in users){
            if(this.code == users[i].code){
                delete users[i];
            }
        }
    },
//编辑用户
    editUserData:function(){
        for(var i in users){
            if(this.code == users[i].code){
                users[i].userName = this.userName;
                users[i].description = this.description;
                users[i].age = this.age;
            }
        }
    },
//查找用户
    findUserData:function(data){

        for(var i in users){
            if(data.code.indexOf(users[i].code) >= 0 ||
                data.userName.indexOf(users[i].userName) >= 0){
                searchUsers[users[i].code] = users[i];
                refreshDatas(searchUsers);
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
function getPersons(){
    $.ajax({
        type:'get',
        async: false,
        url:"http://127.0.0.1:1111/p/",
        success: function (datas) {
            console.log(datas);
            users={}
            for(var i=0;i<datas.data.length;i++){

                var data =datas.data[i];
                var initUser = New(User,[data.id,data.name,data.age,data.description]);
                users[initUser.code] = initUser;
            }

            // addRowData(users);
            refreshDatas(users);
        },
        error: function(){
            alert('请稍后重试');
        }

    })
}

/**
 * 向后台查询单个人
 */
function getPerson(id){
    $.ajax({
        type:'get',
        async: false,
        url:"http://127.0.0.1:1111/p/"+id,
        success: function (datas) {
            console.log(datas);
            users={}
            for(var i=0;i<datas.data.length;i++){

                var data =datas.data[i];
                var initUser = New(User,[data.id,data.name,data.age,data.description]);
                users[initUser.code] = initUser;
            }
            console.log(users);
            // addRowData(users);
            refreshDatas(users);
        },
        error: function(){
            alert('请稍后重试');
        }

    })
}


/**
 * 向后台添加Person
 */

function addPerson(body){
    $.ajax({
        type:'post',
        async: false,
        url:"http://127.0.0.1:1111/p/add",
        data:JSON.stringify(body),
        contentType:'application/json;charset=UTF-8',
        success: function (ret) {
            console.log(ret);
            initUserDatas();
        },
        error: function(){
            alert('请稍后重试');
        }

    })
}

/**
 * 向后台修改Person
 */
function editPerson(body){
    $.ajax({
        type:'put',
        async: false,
        url:"http://127.0.0.1:1111/p/",
        data:JSON.stringify(body),
        contentType:'application/json;charset=UTF-8',
        success: function (data) {
            console.log(data)
            initUserDatas();
        },
        error: function(){
            alert('请稍后重试');
        }

    })
}

/**
 * 向后台删除Person
 */
function deletePerson(user){

    id = user.code;
    $.ajax({
        type:'delete',
        async: false,
        url:"http://127.0.0.1:1111/p/"+id,
        data:null,
        contentType:'application/json;charset=UTF-8',
        success: function (datas) {
            console.log(datas);
            users={}
            for(var i=0;i<datas.data.length;i++){

                var data =datas.data[i];
                var initUser = New(User,[data.id,data.name,data.age,data.description]);
                users[initUser.code] = initUser;
            }

            // addRowData(users);
            refreshDatas(users);
        },
        error: function(){
            alert('请稍后重试');
        }

    })

}



/**
 * 初始化用户数据
 */
function initUserDatas(){

    console.log('initUserDatas')
    getPersons();

    // return users;
}

//bootstrap模态框事件
$('#myModal').on('hide.bs.modal', function () {
// 执行一些动作...
    var inputElements = this.getElementsByTagName("input");
    var userArr = [];
    for(var i=0;i<inputElements.length;i++){
        userArr[i] = inputElements[i].value;
    }
    var user = New(User,userArr);
//添加操作
    if(operateType == "add"){
        var person = {};
        person.name = user.userName;
        person.age = user.age;
        person.description = user.description;
        addPerson(person)

        // user.addUserData();
        refreshDatas(users);
//编辑操作
    }else if(operateType == "edit"){
        // user.editUserData();
        var person = {};
        person.id = user.code;
        person.name = user.userName;
        person.age = user.age;
        person.description = user.description;
        editPerson(person);
        // refreshDatas(users);
    }
});

/**
 * 首次加载页面执行方法
 */
function loadUserDatas(){
    initUserDatas();

    // var userArray = initUserDatas();

    // addRowData(userArray);
    // refreshDatas(users);

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
        html = html + "<tr class='"+ color +"'><td style='width:50px;'><input type='checkbox'></td><td id='code'>"
            + datas[i].code +"</td><td id='userName'>"
            + datas[i].userName +"</td><td id='age'>"
            + datas[i].age +"</td><td id='description'>"
            + datas[i].description +"</td>"
            +"</tr>";

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
    var userArr = [];
    for(var i=1;i<tdElement.length;i++){
        var temp = tdElement[i].textContent;
        userArr[i-1] = temp;
    }
    var user = New(User,userArr);
    return user;
}
/**
 * 用户操作方法
 */
function optionUserData(param){
//获得操作类别
    var optionType = param.getAttribute("id");
    if(optionType == "user_add"){
        operateType = "add";
    }else if(optionType == "user_delete"){
        var checkRowData = isCheckedData();
        var user = collectionRowData(checkRowData);
        deletePerson(user);

        // user.deleteUserData();
        // refreshDatas(users);
    }else if(optionType == "user_edit"){
        operateType = "edit";
        var checkRowData = isCheckedData();
        var user = collectionRowData(checkRowData);
        var modal_body = document.getElementById("modal-body");
        var inputElements= modal_body.getElementsByTagName("input");
        for(var i=0;i<inputElements.length;i++){
            var temp = inputElements[i].id.substring(2,inputElements[i].id.length)
            inputElements[i].value = user[temp];
        }
    }else if(optionType == "user_find"){
        var s_code = document.getElementById("s_code").value;
        var s_userName = document.getElementById("s_userName").value;
        var s_all= document.getElementById("s_all").value;
//搜索数据
        var s_data = s_data || {};
        console.log(s_code);
        s_data.code = s_code;
        if(s_code != ""){
            getPerson(s_code)
        }
        s_data.userName = s_userName;
        s_data.all = s_all;

        var user = New(User,[]);
        user.findUserData(s_data);
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