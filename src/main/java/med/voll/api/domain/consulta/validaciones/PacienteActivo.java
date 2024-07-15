package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class PacienteActivo implements ValidadorDeConsultas {
    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DatosAgendarConsulta datos) {
        if (datos.idPaciente() == null) {
            return;
        }
        var pacienteActivo = pacienteRepository.findActivoById(datos.idPaciente());
        if (!pacienteActivo) {
            throw new ValidationException("No se puede permitir agendar citas con pacientes inactivos en el sistema.");
        }
    }
}