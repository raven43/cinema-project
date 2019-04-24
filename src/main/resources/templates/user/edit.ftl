<#import "../parts/common.ftl" as common>
<#import "common.ftl" as profile>

<@profile.page "Profile Edit" 2>

    <#if message??>
    <h1 class="alert alert-info my-2">${message}</h1>
    </#if>

    <form class="need-validation" method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}">

        <div class="form-group">
            <label for="login-inp">Login</label>
            <input type="text" class="form-control" id="login-inp"
                   name="login" placeholder="Login" value="${user.username}">
            <div class="valid-feedback"> Looks good!</div>
            <div class="invalid-feedback"> Go to hell!</div>
        </div>
        <div class="form-group form-inline">
            <label for="old-password-inp" class="mx-2">Old password</label>
            <input type="password" class="form-control" id="old-password-inp"
                   name="oldPassword" placeholder="">
            <label for="new-password-inp" class="mx-2">New password</label>
            <input type="password" class="form-control" id="new-password-inp"
                   name="newPassword" placeholder="">
        </div>

        <div class="form-group input-group">
            <div class="input-group-prepend">
                <span class="input-group-text" id="fileInpAddon">Avatar</span>
            </div>
            <div class="custom-file">
                <input type="file" class="custom-file-input" id="fileInp" name="file"
                       aria-describedby="fileInpAddon" onchange="validateFileSize(this,2)">
                <label class="custom-file-label" for="fileInp">Choose file</label>
            </div>
        </div>

        <div class="form-group input-group">
            <button type="submit" class="btn btn-primary">Upload</button>
        </div>
    </form>


</@profile.page>