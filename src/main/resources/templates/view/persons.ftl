<#import "../parts/common.ftl" as common>
<#import "common.ftl" as view>

<@common.page "Films">

    <div class="card-header mb-3">
        <h1 class="card-title">Persons</h1>
        <form method="get" class="">
            <div class="form-group form-row no-gutters m-0">
                <input class="form-control col mr-1" type="text" name="str" placeholder="Search..."
                       value="${str?if_exists}">
                <#if (page.pageable.pageSize!=10)>
                <input type="hidden" name="size" value="${page.size}">
                </#if>
                <button type="submit" class="btn btn-outline-info col-auto ml-1">Search</button>
            </div>
        </form>
    </div>

    <#if (page.totalPages>1)>
        <@common.paggination "persons" page.number page.size page.totalPages></@common.paggination>
    </#if>
    <ul class="list-group">
    <#list page.content as item>
        <@view.pepson_list_item item></@view.pepson_list_item>
    </#list>
    </ul>
    <#if (page.totalPages>1)>
        <@common.paggination "persons" page.number page.size page.totalPages></@common.paggination>
    </#if>

</@common.page>