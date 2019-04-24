<#import "../parts/common.ftl" as common>
<#import "common.ftl" as view>
<#include "../parts/security.ftl">


<@common.page entity.name>

    <div class="card-header mb-3">
        <h1 class="card-title">${entity.name}</h1>
        <@view.view_nav type entity.id 1></@view.view_nav>
    </div>

    <#if isAuthorize>
    <form class="px-3" method="post">
        <div class="form-group">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <textarea class="form-control" name="text" maxlength="512" rows="4"
                      placeholder="Your comment"></textarea>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-outline-info">Send</button>
        </div>
    </form>
    </#if>

    <#if (page.totalPages>1)>
        <@common.paggination "persons" page.number page.size page.totalPages></@common.paggination>
    </#if>

        <ul class="list-group">
    <#list page.content as item>
        <@view.comment item></@view.comment>
    </#list>
        </ul>

    <#if (page.totalPages>1)>
        <@common.paggination "persons" page.number page.size page.totalPages></@common.paggination>
    </#if>


</@common.page>