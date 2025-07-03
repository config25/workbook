package workbook.spring1.apiPayload.exception.handler;

import workbook.spring1.apiPayload.code.BaseErrorCode;
import workbook.spring1.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
