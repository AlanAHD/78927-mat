package mx.uv.practica04;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.saludos.BuscarRequest;
import https.t4is_uv_mx.saludos.BuscarResponse;
import https.t4is_uv_mx.saludos.EliminarRequest;
import https.t4is_uv_mx.saludos.EliminarResponse;
import https.t4is_uv_mx.saludos.ModificarRequest;
import https.t4is_uv_mx.saludos.ModificarResponse;
import https.t4is_uv_mx.saludos.SaludarRequest;
import https.t4is_uv_mx.saludos.SaludarResponse;
import https.t4is_uv_mx.saludos.VerResponse; 

@Endpoint
public class EndPoint{

    List<String> msj = new ArrayList<String>();
    String nombres;
    @Autowired
    private ISaludador iSaludador;

    @PayloadRoot(localPart = "SaludarRequest", namespace = "https://t4is.uv.mx/saludos")

    @ResponsePayload
    public SaludarResponse Saludar(@RequestPayload SaludarRequest peticion){
        SaludarResponse respuesta = new SaludarResponse();
        respuesta.setRespuesta("hola "+peticion.getNombre());
        msj.add(peticion.getNombre());

        Saludador saludador = new Saludador();
        saludador.setNombre(peticion.getNombre());
        iSaludador.save(saludador);

        return respuesta;
    }

    @PayloadRoot(localPart = "BuscarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public BuscarResponse Saludar(@RequestPayload BuscarRequest peticion){
        BuscarResponse respuesta = new BuscarResponse();
        if(msj == null || msj.size() == 0)
        {
            respuesta.setRespuesta("Lista vacia");
        }else{
            Saludador saludador = iSaludador.findById(peticion.getId()).get();
            respuesta.setRespuesta("Se ha modificado");
        }
        return respuesta;
    }

    @PayloadRoot(localPart = "ModificarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public ModificarResponse Modificar(@RequestPayload ModificarRequest peticion){
        ModificarResponse respuesta = new ModificarResponse();
        if(msj == null || msj.size() == 0)
        {
            respuesta.setRespuesta("Lista vacia");
        }else{
            Saludador saludador = new Saludador();
            saludador.setId(peticion.getId());
            saludador.setNombre(peticion.getNombre());
            iSaludador.save(saludador);
            respuesta.setRespuesta("nombre actualizado");
        }
        return respuesta;
    }

    @PayloadRoot(localPart = "VerRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public VerResponse Lista(){
        VerResponse respuesta = new VerResponse();
        if(msj == null || msj.size() == 0)
        {
            respuesta.setRespuesta("Lista vacia");
        }else{
            Iterable <Saludador> lista = iSaludador.findAll();
            String msj = "";
            for(Saludador l : lista){
                msj += "Nombre: " + l.getNombre() + ", ";
            }
            respuesta.setRespuesta(msj);
                }
        return respuesta;
    }

    @PayloadRoot(localPart = "EliminarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public EliminarResponse Eliminar(@RequestPayload EliminarRequest peticion){
        EliminarResponse respuesta = new EliminarResponse();
        if(msj == null || msj.size() == 0)
        {
            respuesta.setRespuesta("Lista vacia");
        }else{
            iSaludador.deleteById(peticion.getId());
            respuesta.setRespuesta("nombre eliminado");
        }
        return respuesta;
    }
}