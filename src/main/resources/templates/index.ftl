<#import "parts/common.ftl" as common>
<#import "view/common.ftl" as view>
<@common.wide_page "Cinema">
    <main class="container">
        <div class="card-deck">
            <#if page??&&page.content??>
                <#list page.content as item>
                    <div class="card">
                        <a href="/films/${item.id}">

                            <img class="card-img-top"
                                 src="/file/<#if item.imgName??>${item.imgName}<#else>default.png</#if>"
                                 alt="Card image cap">
                        </a>
                        <div class="card-body">
                            <h5 class="card-title">${item.name}</h5>
                            <p class="card-text">
                                <#if item.description??>
                                    ${item.description?substring(0,60)}
                                </#if>
                            </p>
                            <p class="card-text">
                                <small class="text-muted">${item.premiere?date}</small>
                            </p>
                        </div>
                    </div>
                </#list>
            </#if>
        </div>
    </main>
</@common.wide_page>