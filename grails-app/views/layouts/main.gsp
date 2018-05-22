<!doctype html>
<html lang="en" class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <title>
            <g:layoutTitle default="Gradr"/>
        </title>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />

        <asset:stylesheet src="application.css"/>

        <g:layoutHead/>
    </head>
    <body class="failing">
        <div class="outer">
           <div class="middle">
               <div class="container main-wrapper">
                   <header class="header">
                       <span class="logo-wrapper">
                           <a class="logo" href="${createLink(uri: '/')}"><g:logo class="test"/>Gradr</a>
                       </span>
                       <span class="nav-wrapper">
                           <nav>
                               <g:applyLayout name="navbar">
                                   <g:pageProperty name="page.navbar" />
                               </g:applyLayout>
                           </nav>
                           <g:if test="${session.user}">
                               <p id="logoutWrapper">
                                   ${session.user.name()}
                                   <a href="/user/logout">Logout</a>
                               </p>
                           </g:if>
                       </span>
                   </header>
                   <section class="content-wrapper">
                       <g:layoutBody/>
                   </section>
                   <footer class="footer">

                   </footer>
               </div>
           </div>
        </div>

    <asset:javascript src="application.js"/>

    </body>
</html>
