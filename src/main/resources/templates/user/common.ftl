<#import "../parts/common.ftl" as common>

<#macro page title tab>

    <@common.page title>
        <div class="card-header mb-3">
            <h1 class="card-title">${title}</h1>
            <ul class="nav nav-tabs card-header-tabs">
                <li class="nav-item">
                    <a class="nav-link${(tab==0)?string(" active","")}" href="/profile">Main</a>
                </li>
                <li class="nav-item disabled">
                    <a class="nav-link${(tab==1)?string(" active","")}" href="/profile/im">Messages</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link${(tab==2)?string(" active","")}" href="/profile/edit">Edit</a>
                </li>
            </ul>
        </div>
        <div class="px-3">
        <#nested >
        </div>
    </@common.page>

</#macro>

<#macro user_view item>
    <div class="row m-0">
        <div class="col-auto">
            <img src="/file/<#if item.imgName??>${item.imgName}<#else>default.png</#if>" class="icon-lg">
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
                <#if item.description??><div class="card-text mb-3">${item.description}</div></#if>

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

<#macro user_item_view user>

</#macro>

<#macro chat_view chat>

</#macro>

<#macro chat_item_view chat>

</#macro>

<#macro message_incoming message>

</#macro>

<#macro message_outgoing message>

</#macro>