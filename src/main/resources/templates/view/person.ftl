<#import "../parts/common.ftl" as common>
<#import "common.ftl" as view>

<@common.page item.name>
    <div class="card-header mb-3">
        <h1 class="card-title">${item.name}</h1>
        <@view.view_nav "persons" item.id 0></@view.view_nav>
    </div>
    <@view.person_view item></@view.person_view>
</@common.page>