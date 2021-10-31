<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigate.jspf"%>

<div class="container mt-4">
    <div class="row">
        <div class="col-sm-4">
            <div class="card">
                <div class="card-body">
                    <form action="Controlador?menu=Empleado" method="POST">
                        <div class="form-group">
                            <label>Dni</label>
                            <input type="text" value="${empleado.getDni()}" name="txtDni" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Nombres</label>
                            <input type="text" value="${empleado.getNom()}" name="txtNombres" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Telefono</label>
                            <input type="text" value="${empleado.getTel()}" name="txtTel" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Estado</label>
                            <select class="form-control" name="txtEstado">
                                <option value="1">ACTIVO</option>
                                <option value="0">INACTIVO</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Usuario</label>
                            <input type="text" value="${empleado.getUser()}" name="txtUser" class="form-control">
                        </div>
                        <input type="submit" name="accion" value="Agregar" class="btn btn-primary">
                        <input type="submit" name="accion" value="Actualizar" class="btn btn-success">
                    </form>
                </div>                         
            </div>
        </div>                     
        <div class="col-sm-8">
            <div class="card">
                <div class="card-body">
                    <table class="table table-hover" style="width: 100%" id="example">
                        <thead>
                            <tr>
                                <th>#</th>                                
                                <th>NOMBRES</th>
                                <th>DNI</th>
                                <th>TELEFONO</th>
                                <th>ESTADO</th>
                                <th>USER</th>
                                <th>ACCION</th>
                            </tr>
                        </thead>
                        <tbody> 
                            <c:forEach var="em" items="${empleados}" varStatus="n">
                                <tr>
                                    <td>${n.index+1}</td>                                    
                                    <td>${em.getNom()}</td>
                                    <td>${em.getDni()}</td>
                                    <td>${em.getTel()}</td>
                                    <c:if test="${em.getEstado()=='1'}">
                                        <td>ACTIVO</td>
                                    </c:if>
                                    <c:if test="${em.getEstado()=='0'}">
                                        <td>INACTIVO</td>
                                    </c:if>                                    
                                    <td>${em.getUser()}</td>
                                    <td>
                                        <a class="btn btn-outline-warning btn-sm" href="Controlador?menu=Empleado&accion=Editar&id=${em.getId()}"><i class="bi bi-trash"></i></a>                                        
                                        <a class="btn btn-outline-danger btn-sm" href="Controlador?menu=Empleado&accion=Delete&id=${em.getId()}"><i class="bi bi-pencil-square"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>      
</div>      
<%@ include file="commons/footer.jspf"%>

