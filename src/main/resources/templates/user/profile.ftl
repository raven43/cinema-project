<#import "../parts/common.ftl" as common>
<#import "common.ftl" as profile>
<#include "../parts/security.ftl">
<@profile.page user.username 0>

    <img src="/file/<#if user.imgName??>${user.imgName}<#else >default.png</#if>" class="icon-md">

    <h1 class="d-inline-block">${user.username}</h1>

</@profile.page>