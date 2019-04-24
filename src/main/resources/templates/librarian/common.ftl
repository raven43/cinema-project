<#import "../parts/common.ftl" as common>

<#macro page title>
    <@common.page title>
        <nav class="navbar navbar-expand-md navbar-light bg-light mb-3">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item disabled">
                        <a class="nav-link" href="/librarian/register">Register Borrowing</a>
                    </li>
                    <li class="nav-item disabled">
                        <a class="nav-link" href="/librarian/update">Update Borrowing</a>
                    </li>
                </ul>
            </div>
        </nav>
        <#nested >

    </@common.page>
</#macro>

<#macro user_list_item usr>

    <div class="row my-2">
        <div class="col-auto">
            <a href="/librarian/${usr.id}">
            <#if usr.imgName??>
                <img src="/file/${usr.imgName}" class="icon-r-sm">
            <#else >
            <img src="/file/default.png" class="icon-r-sm">
            </#if>
            </a>

        </div>
        <div class="col">
            <div class="card">
                <h5 class="card-header py-2">
                    <a href="/librarian/${usr.id}">${usr.username}</a>
                </h5>
                <div class="card-body w-100 text-muted py-2">
                <#list usr.roles as role>${role}<#sep >, </#list>
                </div>
            </div>
        </div>
    </div>

</#macro>

<#macro client_view client>

    <div class="row my-2">
        <div class="col-auto">
            <a href="/librarian/${client.id}">
            <#if client.imgName??>
                <img src="/file/${client.imgName}" class="icon-r-lg">
            <#else >
            <img src="/file/default.png" class="icon-r-lg">
            </#if>
            </a>

        </div>
        <div class="col">
            <div class="card">
                <h3 class="card-header py-2">
                    <a href="/librarian/${client.id}">${client.username}</a>
                </h3>
                <div class="card-body w-100 py-2">

                    <form method="post">
                        <div class="form-group">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="client-check" disabled checked>
                                <label class="form-check-label" for="client-check">
                                    Client
                                </label>
                            </div>
                            <div class="form-check">
                                <input name="lib" class="form-check-input" type="checkbox" value="true"
                                       id="lib-check" ${client.roles?seq_contains("LIBRARIAN")?string("checked","")}>
                                <label class="form-check-label" for="lib-check">
                                    Librarian
                                </label>
                            </div>
                            <div class="form-check">
                                <input name="adm" class="form-check-input" type="checkbox" value="true"
                                       id="adm-check" ${client.roles?seq_contains("ADMIN")?string("checked","")}>
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
                </div>
            </div>
        </div>
    </div>

</#macro>
