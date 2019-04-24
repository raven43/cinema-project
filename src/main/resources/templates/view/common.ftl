<#include "../parts/security.ftl">

<#macro film_list_item item>

    <div class="card flex-row flex-wrap m-2">
        <div class="card-header border-0">
            <a class="d-block" href="/films/${item.id}">
            <#if item.imgName??>
                <img src="/file/${item.imgName}" class="icon-md">
            <#else >
                <img src="/file/default.png" class="icon-md">
            </#if>
            </a>
        </div>
        <div class="card-block p-2">
            <h4 class="card-title d-flex flex-row">
                <a href="/films/${item.id}" class="nav-link">${item.name}</a>
                <#if isAdmin>
                <a href="/admin/film?id=${item.id}" class="btn btn-outline-info">Edit</a>
                </#if>

            </h4>
            <div class="card-text">
                <nav class="text-secondary">
                    <#list item.roles as role>
                        <a class="page-link d-inline" href="/persons/${role.person.id}">
                            <img class="icon-xxs-r d-inline"
                                 src="/file/<#if role.person.imgName??>${role.person.imgName}<#else >default.png</#if>">
                            ${role.person.name}
                        </a>
                    <#sep > </#list>
                </nav>
            </div>
        </div>
    </div>

</#macro>

<#macro pepson_list_item item>

    <div class="card flex-row flex-wrap m-2">
        <div class="card-header border-0">
            <a class="d-block" href="/persons/${item.id}">
            <#if item.imgName??>
                <img src="/file/${item.imgName}" class="icon-md">
            <#else >
                <img src="/file/default.png" class="icon-md">
            </#if>
            </a>
        </div>
        <div class="card-block p-2">
            <h4 class="card-title d-flex flex-row">
                <a href="/persons/${item.id}" class="nav-link">${item.name}</a>
                <#if isAdmin>
                <a href="/admin/person?id=${item.id}" class="btn btn-outline-info">Edit</a>
                </#if>

            </h4>
            <div class="card-text">
                <nav class="text-secondary">
                    <#list item.roles as role>
                        <a class="page-link d-inline" href="/films/${role.film.id}">
                            <img class="icon-xxs-r d-inline"
                                 src="/file/<#if role.film.imgName??>${role.film.imgName}<#else >default.png</#if>">
                            ${role.film.name}
                        </a>
                    <#sep > </#list>
                </nav>
            </div>
        </div>
    </div>

</#macro>

<#macro film_view item>

    <div class="row m-0">
        <div class="col-auto">
            <#if item.imgName??>
                <img src="/file/${item.imgName}" class="icon-lg">
            <#else >
            <img src="/file/default.png" class="icon-lg">
            </#if>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header py-2">
                    <h3 class="card-title">
                        ${item.name}
                        <#if isAdmin>
                        <a href="/admin/film?id=${item.id}" class="btn btn-outline-info">Edit</a>
                        </#if>
                    </h3>
                </div>
                <div class="card-body">
                    <#if item.description??>
                        <div class="card-text mb-3">${item.description}</div>                     </#if>

                    <#if item.roles??>
                        <div>
                            <h5 class="card-title">Cast</h5>
                            <table class="table table-striped table-bordered">
                                <thead>
                                <tr>
                                    <td>Person</td>
                                    <td>Role</td>
                                </tr>
                                </thead>
                                <tbody>
                                    <#list item.roles as role>
                                    <tr>
                                        <td><a href="/persons/${role.person.id}">${role.person.name}</a></td>
                                        <td>${role.description}</td>
                                    </tr>
                                    </#list>
                                </tbody>
                            </table>
                        </div>
                    </#if>
                </div>
            </div>
        </div>
    </div>

</#macro>

<#macro person_view item>

    <div class="row m-0">
        <div class="col-auto">
            <#if item.imgName??>
                <img src="/file/${item.imgName}" class="icon-lg">
            <#else >
            <img src="/file/default.png" class="icon-lg">
            </#if>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header py-2">
                    <h3 class="card-title">
                        ${item.name}
                        <#if isAdmin>
                        <a href="/admin/person?id=${item.id}" class="btn btn-outline-info">Edit</a>
                        </#if>
                    </h3>
                </div>
                <div class="card-body">
                    <#if item.description??>
                        <div class="card-text mb-3">${item.description}</div>                     </#if>

                    <#if item.roles??>
                        <div>
                            <h5 class="card-title">Roles</h5>
                            <table class="table table-striped  table-bordered">
                                <thead>
                                <tr>
                                    <td>Film</td>
                                    <td>Role</td>
                                </tr>
                                </thead>
                                <tbody>
                                    <#list item.roles as role>
                                    <tr>
                                        <td><a href="/films/${role.film.id}">${role.film.name}</a></td>
                                        <td>${role.description}</td>
                                    </tr>
                                    </#list>
                                </tbody>
                            </table>
                        </div>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</#macro>

<#macro view_nav type id tab>

    <ul class="nav nav-tabs card-header-tabs">
        <li class="nav-item">
            <a class="nav-link${(tab==0)?string(" active","")}" href="/${type}/${id}">View</a>
        </li>
        <li class="nav-item">
            <a class="nav-link${(tab==1)?string(" active","")}" href="/${type}/${id}/comments">Comments</a>
        </li>
    </ul>

</#macro>

<#macro comment comment>

    <div class="row my-2 mx-0" id="comment${comment.id}">
        <div class="col-auto">
            <#if comment.user.imgName??>
                <a href="/profile/${comment.user.id}"><img src="/file/${comment.user.imgName}" class="icon-sm-r"></a>
            <#else >
            <img src="/file/default.png" class="icon-sm-r">
            </#if>
        </div>
        <div class="col">
            <div class="card">
                <h5 class="card-header py-2">
                    <a href="/profile/${comment.user.id}">${comment.user.username}</a>
                    <#if isAuthorize&&(isAdmin||(user.id==comment.user.id))>
                    <span id="del${comment.id}" style="text-align: right; cursor: pointer"
                          onclick="deleteComment(${comment.id},'${_csrf.token}')">❌</span>
                    </#if>
                </h5>
                <script>
                </script>
                <div class="card-body">
                    <p class="card-text">${comment.text}</p>
                </div>
                <div class="card-footer w-100 text-muted py-2">
                    ${comment.date?datetime}
                </div>
            </div>
        </div>
    </div>
</#macro>