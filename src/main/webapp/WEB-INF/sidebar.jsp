<%@ page import="models.Category" %>
<%@ page import="java.util.List" %>
<nav id="sidebar">
    <div class="sidebar-header">
        <h3>Fake Amazon</h3>
    </div>

    <ul class="list-unstyled components">
        <p>Dummy Fake Amazon</p>
        <li class="active">
            <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Categories</a>
            <%! List<Category> categories = Category.getAll(); %>
            <ul class="collapse list-unstyled" id="homeSubmenu">
                <% for (Category cat : categories) { %>
                    <li><a href="/?category=<%=cat.getId()%>"><%=cat.getName()%></a></li>
                <%}%>
            </ul>
        </li>
        <li><a href="#">About</a></li>
        <li>
            <a href="#pageSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Pages</a>
            <ul class="collapse list-unstyled" id="pageSubmenu">
                <li><a href="#">Page 1</a></li>
                <li><a href="#">Page 2</a></li>
                <li><a href="#">Page 3</a></li>
            </ul>
        </li>
        <li><a href="#">Portfolio</a></li>
        <li><a href="#">Contact</a></li>
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
        });
    </script>
</nav>