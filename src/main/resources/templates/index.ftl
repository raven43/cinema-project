<#import "parts/common.ftl" as common>
<#import "view/common.ftl" as view>
<@common.wide_page "Cinema">

        <h1 class="card-title m-0 text-light">Welcome To Web Cinema</h1>
<main class="container">
    <div class="card-deck">
        <#if page??&&page.content??>
            <#list page.content as item>
            <div class="card">
                <a href="/films/${item.id}">

                    <img class="card-img-top" src="/file/<#if item.imgName??>${item.imgName}<#else>default.png</#if>"
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
        <#--<div class="card">-->
            <#--<img class="card-img-top" src="/file/default.png"-->
            <#--&lt;#&ndash;style="max-width: 14rem" &ndash;&gt;-->
                 <#--alt="Card image cap">-->
            <#--<div class="card-body">-->
                <#--<h5 class="card-title">Card title</h5>-->
                <#--<p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional-->
                    <#--content. This card has even longer content than the first to show that equal height action.</p>-->
                <#--<p class="card-text">-->
                    <#--<small class="text-muted">Last updated 3 mins ago</small>-->
                <#--</p>-->
            <#--</div>-->
        <#--</div>-->
        <#--<div class="card">-->
            <#--<img class="card-img-top" src="/file/default.png"-->
            <#--&lt;#&ndash;style="max-width: 14rem" &ndash;&gt;-->
                 <#--alt="Card image cap">-->
            <#--<div class="card-body">-->
                <#--<h5 class="card-title">Card title</h5>-->
                <#--<p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional-->
                    <#--content. This card has even longer content than the first to show that equal height action.</p>-->
                <#--<p class="card-text">-->
                    <#--<small class="text-muted">Last updated 3 mins ago</small>-->
                <#--</p>-->
            <#--</div>-->
        <#--</div>-->
        <#--<div class="card">-->
            <#--<img class="card-img-top" src="/file/default.png"-->
            <#--&lt;#&ndash;style="max-width: 14rem" &ndash;&gt;-->
                 <#--alt="Card image cap">-->
            <#--<div class="card-body">-->
                <#--<h5 class="card-title">Card title</h5>-->
                <#--<p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional-->
                    <#--content. This card has even longer content than the first to show that equal height action.</p>-->
                <#--<p class="card-text">-->
                    <#--<small class="text-muted">Last updated 3 mins ago</small>-->
                <#--</p>-->
            <#--</div>-->
        <#--</div>-->
    </div>
</main>

</@common.wide_page>