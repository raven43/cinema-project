<#import "../parts/common.ftl" as common>
<#import "common.ftl" as profile>

<@profile.page item.username 8>

    <img src="/file/<#if item.imgName??>${item.imgName}<#else >default.png</#if>" class="icon-md">

    <h1 class="d-inline-block">${item.username}</h1>

    <button class="btn btn-outline-success" onclick="opnBtn(${item.id},'${_csrf.token}')">Open Chat</button>
    <button class="btn btn-outline-primary" onclick="getBtn.call(this)">Get Chats</button>

    <script>
        function opnBtn(id, token) {
            console.log(this);
            openChat(id, token,function (response) {
               console.log(response);
            });
        }
        function getBtn() {
            console.log(this);
            getChats(function (page) {
                console.log(page);
            });
        }
    </script>
</@profile.page>