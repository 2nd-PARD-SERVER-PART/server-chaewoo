package club.pard.server.assignment03.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "set")
public class ResponseDto<D> {
    private boolean result;
    private String message;
    private D data;

    public static <D> ResponseDto<D> setSuccess(String message, D data){ return ResponseDto.set(true, message, data); }
    public static <D> ResponseDto<D> setFailure(String message){ return ResponseDto.set(false, message, null); }
}
