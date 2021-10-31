<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigate.jspf"%>
<div class="container mt-4">
    <div class="form-inline justify-content-center">
        <div class="form-group">
            <form action="Controlador?menu=NuevaVenta" method="POST">    
                <div class="row text-center">
                    <div class="col-12">
                        <button type="submit" name="accion" value="ventanueva" class="btn btn-lg badge badge-light">
                            <img src="img/cart.png" alt=""/>
                        </button>
                    </div>
                    <div class="col-12">
                        <label><h6>VENTA NUEVA</h6></label>  
                    </div> 
                </div>
            </form>
        </div>
        <div class="form-group">
            <form action="" method="POST">    
                <div class="row text-center">
                    <div class="col-12">
                        <button type="submit" value="" class="btn btn-lg badge badge-light">
                            <img src="img/caja.png" alt=""/>
                        </button>
                    </div>
                    <div class="col-12">
                        <label><h6>CIERRE CAJA</h6></label>  
                    </div> 
                </div>
            </form>
        </div>
        <div class="form-group">
            <form action="" method="POST">    
                <div class="row text-center">
                    <div class="col-12">
                        <button type="submit" value="" class="btn btn-lg badge badge-light">
                            <img src="img/informe.png" alt=""/>
                        </button>
                    </div>
                    <div class="col-12">
                        <label><h6>REPORTES</h6></label>  
                    </div> 
                </div>
            </form>
        </div>
        <div class="form-group">
            <form action="" method="POST">    
                <div class="row text-center">
                    <div class="col-12">
                        <button type="submit" value="" class="btn btn-lg badge badge-light">
                            <img src="img/setting.png" alt=""/>
                        </button>
                    </div>
                    <div class="col-12">
                        <label><h6>OTROS</h6></label>  
                    </div> 
                </div>
            </form>
        </div>
        
    </div>
</div>
<%@ include file="commons/footer.jspf"%>
