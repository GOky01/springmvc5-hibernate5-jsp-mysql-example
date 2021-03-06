<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <meta charset="ISO-8859-1">
    <title>Spring MVC 5 - form handling | Java Guides</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />"
          rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</head>
<body>
<div class="container">
    <div class="col-md-offset-2 col-md-7">
        <h2 class="text-center"></h2>
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Add Phone</div>
            </div>
            <div class="panel-body">

                <form:form action="savePhone" cssClass="form-horizontal"
                           method="post" modelAttribute="phone">

                    <form:hidden path="phone_id"/>

                    <div class="form-group">
                        <label for="phone" class="col-md-3 control-label">
                            Phone Number</label>
                        <div class="col-md-9">
                            <form:input path="phone" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-9">
                            <select name="id" >
                                <c:forEach items="${customers}" var="tempCustomer">
                                    <option value="${tempCustomer.id}">${tempCustomer.first_name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-9">
                            <form:button cssClass="btn btn-primary">Submit</form:button>
                        </div>
                    </div>

                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>