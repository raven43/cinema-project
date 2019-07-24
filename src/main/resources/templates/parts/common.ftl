<#include "security.ftl">
<#macro wide_page title>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>

    <link rel="stylesheet" href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/main.css" type="text/css">

    <script src="/webjars/jquery/3.3.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    <script rel="script" type="application/javascript" src="/static/main.js"></script>
</head>
<body class="bg-secondary">
    <#include "navbar.ftl">
    <#--<main class="bg-white card mx-auto pb-3" style="max-width: 720px">-->
        <#nested>
    <#--</main>-->

</body>
</html>
</#macro>

<#macro page title>
    <@wide_page title>
        <main class="bg-white card mx-auto pb-3" style="max-width: 720px">
        <#nested>
        </main>
    </@wide_page>
</#macro>

<#macro list_item item>
    <div class="list-group-item">

        <img src="/file/<#if item.imgName??>${item.imgName}<#else>default.png</#if>" class="icon-md-r">

        <div class="d-inline-block">
            <h3>${item.name}</h3>
        </div>
    </div>
</#macro>

<#macro paggination entity current size count>
    <div class="" style="width: 100%">
        <nav class="d-inline-block" aria-label="Page navigation">
            <ul class="pagination my-3">
                <li class="page-item${(current==0)?string(" disabled","")}">
                    <a class="page-link" href="/${entity}?pageNumber=0&pageSize=${size}">&laquo;</a>
                </li>
                <li class="page-item${(current==0)?string(" disabled","")}">
                    <a class="page-link" href="/${entity}?pageNumber=${current-1}&pageSize=${size}">&lt;</a>
                </li>
                <#if (current>2)>
                <li class="page-item">
                    <a class="page-link" href="/${entity}?pageNumber=${current-3}&pageSize=${size}">${current-3 +1}</a>
                </li>
                </#if>
                <#if (current>1)>
                <li class="page-item">
                    <a class="page-link" href="/${entity}?pageNumber=${current-2}&pageSize=${size}">${current-2 +1}</a>
                </li>
                </#if>
                <#if (current>0)>
                <li class="page-item">
                    <a class="page-link" href="/${entity}?pageNumber=${current-1}&pageSize=${size}">${current-1 +1}</a>
                </li>
                </#if>

                <li class="page-item active">
                    <a class="page-link" href="/${entity}?pageNumber=${current}&pageSize=${size}">${current +1}</a>
                </li>

                <#if (current<count-1)>
                <li class="page-item">
                    <a class="page-link" href="/${entity}?pageNumber=${current+1}&pageSize=${size}">${current+1 +1}</a>
                </li>
                </#if>
                <#if (current<count-2)>
                <li class="page-item">
                    <a class="page-link" href="/${entity}?pageNumber=${current+2}&pageSize=${size}">${current+2 +1}</a>
                </li>
                </#if>
                <#if (current<count-3)>
                <li class="page-item">
                    <a class="page-link" href="/${entity}?pageNumber=${current+3}&pageSize=${size}">${current+3 +1}</a>
                </li>
                </#if>

                <li class="page-item${(current==count-1)?string(" disabled","")}">
                    <a class="page-link" href="/${entity}?pageNumber=${current+1}&pageSize=${size}">&gt;</a>
                </li>
                <li class="page-item${(current==count-1)?string(" disabled","")}">
                    <a class="page-link" href="/${entity}?pageNumber=${count-1}&pageSize=${size}">&raquo;</a>
                </li>
            </ul>
        </nav>
        <span class="d-inline-block nav-link">${current +1} from ${count}</span>
    </div>
</#macro>

<#macro paggination_alt link page>
    <#if (page.totalPages>1)>
    <div class="" style="width: 100%">
        <nav class="d-inline-block" aria-label="Page navigation">
            <ul class="pagination my-3">
                <li class="page-item${page.first?string(" disabled","")}">
                    <a class="page-link" href="/${link}?page=0&size=${page.size}">&laquo;</a>
                </li>
                <li class="page-item${page.first?string(" disabled","")}">
                    <a class="page-link" href="/${link}?page=${page.number-1}&size=${page.size}">&lt;</a>
                </li>
                <#if (page.number>2)>
                <li class="page-item">
                    <a class="page-link" href="/${link}?page=${page.number-3}&size=${page.size}">${page.number-3 +1}</a>
                </li>
                </#if>
                <#if (page.number>1)>
                <li class="page-item">
                    <a class="page-link" href="/${link}?page=${page.number-2}&size=${page.size}">${page.number-2 +1}</a>
                </li>
                </#if>
                <#if (page.number>0)>
                <li class="page-item">
                    <a class="page-link" href="/${link}?page=${page.number-1}&size=${page.size}">${page.number-1 +1}</a>
                </li>
                </#if>

                <li class="page-item active">
                    <a class="page-link" href="/${link}?page=${page.number}&size=${page.size}">${page.number +1}</a>
                </li>

                <#if (page.number<page.totalPages-1)>
                <li class="page-item">
                    <a class="page-link" href="/${link}?page=${page.number+1}&size=${page.size}">${page.number+1 +1}</a>
                </li>
                </#if>
                <#if (page.number<page.totalPages-2)>
                <li class="page-item">
                    <a class="page-link" href="/${link}?page=${page.number+2}&size=${page.size}">${page.number+2 +1}</a>
                </li>
                </#if>
                <#if (page.number<page.totalPages-3)>
                <li class="page-item">
                    <a class="page-link" href="/${link}?page=${page.number+3}&size=${page.size}">${page.number+3 +1}</a>
                </li>
                </#if>

                <li class="page-item${page.last?string(" disabled","")}">
                    <a class="page-link" href="/${link}?page=${page.number+1}&size=${page.size}">&gt;</a>
                </li>
                <li class="page-item${page.last?string(" disabled","")}">
                    <a class="page-link" href="/${link}?page=${page.totalPages-1}&size=${page.size}">&raquo;</a>
                </li>
            </ul>
        </nav>
        <span class="d-inline-block nav-link">${page.number +1} from ${page.totalPages}</span>
    </div>
    </#if>
</#macro>
