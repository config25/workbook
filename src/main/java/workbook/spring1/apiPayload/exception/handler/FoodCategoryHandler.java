package workbook.spring1.apiPayload.exception.handler;

import workbook.spring1.apiPayload.code.BaseCode;
import workbook.spring1.apiPayload.code.BaseErrorCode;
import workbook.spring1.apiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
