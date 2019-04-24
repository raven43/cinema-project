<#import "common.ftl" as librarian>
<#import "../parts/common.ftl" as common>

<@common.page "Librarian">

    <h1>librarian page</h1>

    <form method="get">
        <div class="form-group form-row no-gutters m-0">
            <input class="form-control col mx-1" type="text" name="str" placeholder="Search..." value="${str}">

            <#if (currentPageNumber!=0)>
            <input type="hidden" name="pageNumber" value="${currentPageNumber}">
            </#if>
            <#if (currentPageSize!=10)>
            <input type="hidden" name="pageSize" value="${currentPageSize}">
            </#if>

            <button type="submit" class="btn btn-outline-info col-auto mx-1">Search</button>
        </div>
    </form>

    <#if (pagesCount>1)>
        <@common.paggination "librarian" currentPageNumber currentPageSize pagesCount></@common.paggination>
    </#if>
    <div>
    <#list users as u>
        <@librarian.user_list_item u></@librarian.user_list_item>
    </#list>
    </div>
    <#if (pagesCount>1)>
        <@common.paggination "librarian" currentPageNumber currentPageSize pagesCount></@common.paggination>
    </#if>

</@common.page>