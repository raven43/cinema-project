<#import "common.ftl" as admin>
<#import "../parts/common.ftl" as common>

<@admin.page "Administrate" 2>

    <div class="card-body p-0 mb-3">
        <h1 class="card-title">Users edit page</h1>
        <form method="get" class="">
            <div class="form-group form-row no-gutters m-0">
                <input class="form-control col mr-1" type="text" name="str" placeholder="Search..." value="${str?if_exists}">
                <#if (page.pageable.pageSize!=10)>
                <input type="hidden" name="size" value="${page.size}">
                </#if>
                <button type="submit" class="btn btn-outline-info col-auto ml-1">Search</button>
            </div>
        </form>
    </div>

    <@common.paggination_alt "admin/users" page></@common.paggination_alt>

    <div>
    <#list page.content as u>
        <@admin.user_list_item u></@admin.user_list_item>
    </#list>
    </div>

    <@common.paggination_alt "admin/users" page></@common.paggination_alt>

</@admin.page>