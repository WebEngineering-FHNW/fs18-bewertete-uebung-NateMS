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
    <body>
   <div class="outer">
       <div class="middle">
           <div class="container main-wrapper">
               <header class="header">
                   <a class="logo" href="${createLink(uri: '/')}">Gradr</a>
                    <g:if test="${session.user}">
                       <p id="logoutWrapper">
                           ${session.user.name()}
                           <a href="/user/logout">Logout</a>
                       </p>
                    </g:if>
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
