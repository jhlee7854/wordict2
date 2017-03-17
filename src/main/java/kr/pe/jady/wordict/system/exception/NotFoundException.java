package kr.pe.jady.wordict.system.exception;

/**
 * 시스템에서 데이터를 찾지 못했을때 발생 시키는 사용자 정의 Exception
 * @author jhlee7854
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
