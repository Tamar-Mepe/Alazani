<%@ page import="models.Category" %>
<%@ page import="java.util.List" %>
<nav id="sidebar">
    <div class="sidebar-header">
        <a href="/index.jsp"><img src="images/alazani.png" width="100%"></a>
    </div>
    <ul class="list-unstyled components">
        <li class="active">
            <a id="href-id" href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Categories</a>
            <%! List<Category> categories = Category.getAll(); %>
            <ul class="collapse list-unstyled" id="homeSubmenu">
                <% for (Category cat : categories) { %>
                <li><a href="/?category=<%=cat.getId()%>"><%=cat.getName()%>
                </a></li>
                <%}%>
            </ul>
        </li>
        <li><a href="/contacts.jsp">About Us</a></li>
    </ul>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#sidebar").mCustomScrollbar({
                theme: "minimal"
            });

            $('#sidebarCollapse').on('click', function () {
                $('#sidebar, #content').toggleClass('active');
                $('.collapse.in').toggleClass('in');
                $('a[aria-expanded=true]').attr('aria-expanded', 'false');
            });
            $('#href-id').click();
        });
    </script>
</nav>