<#import "../parts/common.ftl" as common>

<#macro page title tab>
    <@common.page title>
        <div class="card-header mb-3">

            <ul class="nav nav-tabs card-header-tabs">
                <li class="nav-item">
                    <a class="nav-link${(tab==0)?string(" active","")}" href="/admin/film">Edit Film</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link${(tab==1)?string(" active","")}" href="/admin/person">Edit Person</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link${(tab==2)?string(" active","")}" href="/admin/users">Edit Users</a>
                </li>
            </ul>
        </div>
        <div class="px-3">
            <#nested>
        </div>
    </@common.page>

</#macro>

<#macro admin_nav tab>
        <div class="card-header mb-3">
            <ul class="nav nav-tabs card-header-tabs">
                <li class="nav-item">
                    <a class="nav-link${(tab==0)?string(" active","")}" href="/admin/film">Edit Film</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link${(tab==1)?string(" active","")}" href="/admin/person">Edit Person</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link${(tab==2)?string(" active","")}" href="/admin/users">Edit Users</a>
                </li>
            </ul>
            <#nested>
        </div>

</#macro>

<#macro user_list_item usr>

    <div class="row my-2 mx-0">
        <div class="col-auto">
            <a href="/admin/users/${usr.id}">
                <img src="/file/<#if usr.imgName??>${usr.imgName}<#else>default.png</#if>" class="icon-sm">
            </a>

        </div>
        <div class="col">
            <div class="card">
                <h5 class="card-header py-2">
                    <a href="/admin/users/${usr.id}">${usr.username}</a>
                </h5>
                <div class="card-body w-100 text-muted py-2">
                <#list usr.roles as role>${role}<#sep >, </#list>
                </div>
            </div>
        </div>
    </div>

</#macro>

<#macro user_edit_view usr>

    <div class="row my-2">
        <div class="col-auto">
            <a href="/admin/users/${usr.id}">
                <img src="/file/<#if usr.imgName??>${usr.imgName}<#else>default.png</#if>" class="icon-lg">
            </a>

        </div>
        <div class="col">
            <div class="card">
                <h3 class="card-header py-2">
                    <a href="/profile/${usr.id}">${usr.username}</a>
                </h3>
                <div class="card-body w-100 py-2">

                    <form method="post">
                        <div class="form-group">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="client-check" disabled checked>
                                <label class="form-check-label" for="client-check">
                                    User
                                </label>
                            </div>
                            <div class="form-check">
                                <input name="mod" class="form-check-input" type="checkbox" value="true"
                                       id="mod-check" ${usr.roles?seq_contains("MODER")?string("checked","")}>
                                <label class="form-check-label" for="mod-check">
                                    Moder
                                </label>
                            </div>
                            <div class="form-check">
                                <input name="adm" class="form-check-input" type="checkbox" value="true"
                                       id="adm-check" ${usr.roles?seq_contains("ADMIN")?string("checked","")}>
                                <label class="form-check-label" for="adm-check">
                                    Admin
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <button type="submit" class="btn btn-primary">Update</button>
                        </div>
                    </form>
                <#list usr.roles as role>${role}<#sep >, </#list>
                </div>
            </div>
        </div>
    </div>

</#macro>


