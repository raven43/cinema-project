<#import "parts/common.ftl" as common>

<@common.page "Register">

    <#if message??>
    <div class="alert alert-info my-3">${message}</div>
    </#if>

    <form class="m-auto align-content-center" style="max-width: 16rem" action="/register" method="post">
        <h1 class="h3 mb-3 font-weight-normal">Please Register</h1>
        <div class="form-group">
            <label for="login-inp">Username</label>
            <input name="username" type="text" class="form-control" id="login-inp" placeholder="Username">
        </div>
        <div class="form-group">
            <label for="password-inp">Password</label>
            <input name="password" type="password" class="form-control" id="password-inp"
                   placeholder="Password">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary mr-2" autofocus>Register</button>
        <a href="/login"> Log in</a>
    </form>
</@common.page>