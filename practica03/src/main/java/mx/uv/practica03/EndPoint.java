package mx.uv.practica03;

import java.util.ArrayList;
import java.util.List;

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

    List<String> mensaje = new ArrayList<String>();
    String nombres;

    @PayloadRoot(localPart = "SaludarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public SaludarResponse Saludar(@RequestPayload SaludarRequest peticion){
        SaludarResponse respuesta = new SaludarResponse();
        respuesta.setRespuesta("Bienvenido: "+peticion.getNombre());
        mensaje.add(peticion.getNombre());
        return respuesta;
    }

    @PayloadRoot(localPart = "BuscarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public BuscarResponse Saludar(@RequestPayload BuscarRequest peticion){
        BuscarResponse respuesta = new BuscarResponse();
        if(mensaje == null || mensaje.size() == 0)
        {
            respuesta.setRespuesta("No hay nada");
        }else{
            respuesta.setRespuesta(mensaje.get(peticion.getId()));
        }
        return respuesta;
    }

    @PayloadRoot(localPart = "ModificarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public ModificarResponse Modificar(@RequestPayload ModificarRequest peticion){
        ModificarResponse respuesta = new ModificarResponse();
        if(mensaje == null || mensaje.size() == 0)
        {
            respuesta.setRespuesta("No hay nada");
        }else{
            mensaje.set(peticion.getId(),peticion.getNombre());
            respuesta.setRespuesta("La posición: "+peticion.getId()+" se modifico con el nombre: "+mensaje.get(peticion.getId()));
        }
        return respuesta;
    }

    @PayloadRoot(localPart = "VerRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public VerResponse Modificar(){
        VerResponse respuesta = new VerResponse();
        if(mensaje == null || mensaje.size() == 0)
        {
            respuesta.setRespuesta("No hay nada");
        }else{
            nombres = "Saludo a: ";
            for(String n :mensaje) {
                nombres += n;
                nombres += ", ";
            }
        respuesta.setRespuesta(nombres);
        }
        return respuesta;
    }

    @PayloadRoot(localPart = "EliminarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public EliminarResponse Eliminar(@RequestPayload EliminarRequest peticion){
        EliminarResponse respuesta = new EliminarResponse();
        if(mensaje == null || mensaje.size() == 0)
        {
            respuesta.setRespuesta("No hay nada");
        }else{
            String aux = mensaje.get(peticion.getId());
            mensaje.remove(peticion.getId());
            respuesta.setRespuesta("Se elimino el saludo: "+aux);
        }
        return respuesta;
    }
}