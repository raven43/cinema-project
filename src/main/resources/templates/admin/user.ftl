<#import "common.ftl" as common>

<@common.page "Edit user "+item.username 2>

    <h1>Edit User ${item.username}</h1>
    <#if message??>
    <div class="alert-info alert">${message}</div>
    </#if>
    <@common.user_edit_view item></@common.user_edit_view>

</@common.page>