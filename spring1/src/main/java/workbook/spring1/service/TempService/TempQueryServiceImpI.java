package workbook.spring1.service.TempService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import workbook.spring1.apiPayload.code.status.ErrorStatus;
import workbook.spring1.apiPayload.exception.handler.TempHandler;

@Service
@RequiredArgsConstructor
public class TempQueryServiceImpI implements TempQueryService {
    @Override
    public void CheckFlag(Integer flag) {
        if (flag == 1)
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
    }
}
