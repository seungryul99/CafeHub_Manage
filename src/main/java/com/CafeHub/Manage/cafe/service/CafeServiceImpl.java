package com.CafeHub.Manage.cafe.service;

import com.CafeHub.Manage.cafe.entity.Cafe;
import com.CafeHub.Manage.cafe.entity.Theme;
import com.CafeHub.Manage.cafe.repository.CafeRepository;
import com.CafeHub.Manage.cafe.request.*;
import com.CafeHub.Manage.cafe.response.AllCafeGetResponse;
import com.CafeHub.Manage.cafe.response.CafeInfoResponse;
import com.CafeHub.Manage.cafe.response.CafeResponse;
import com.CafeHub.Manage.common.Image;
import com.CafeHub.Manage.s3.S3Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CafeServiceImpl implements CafeService{

    private final CafeRepository cafeRepository;

    private final S3Manager s3Manager;


    @Override
    public AllCafeGetResponse getAllCafeList(AllCafeGetRequest request) {

        Pageable pageable
                = PageRequest.of(request.getPage() - 1, request.getSize(), Sort.by(Sort.Direction.DESC, "id"));

        Page<Cafe> cafes = cafeRepository.findAll(pageable);

        List<CafeResponse> cafeList = cafes.stream()
                .map(cafe -> new CafeResponse(cafe.getId(), cafe.getName(), cafe.getTheme()))
                .toList();

        return new AllCafeGetResponse(
                cafeList,
                cafes.getTotalPages(),
                cafes.getNumber(),
                cafes.getSize(),
                cafes.isFirst(),
                cafes.isLast()
        );
    }



    @Override
    public CafeInfoResponse getCafeInfo(CafeInfoRequest request) {
        return cafeRepository.findById(request.getCafeId())
                .map(cafe -> new CafeInfoResponse(
                        cafe.getId(),
                        cafe.getName(),
                        cafe.getAddress(),
                        cafe.getCafeImg().getUrl(),
                        cafe.getPhone(),
                        cafe.getRating(),
                        cafe.getReviewCnt(),
                        cafe.getOperationHours(),
                        cafe.getCloseDays(),
                        cafe.getTheme()
                ))
                .orElseThrow(() -> new RuntimeException("에러처리는 나중에 해당 카페id로 카페 정보를 찾을 수 없음: " + request.getCafeId()));
    }


    // 일단 단순 업로드만 가능
    @Override
    @Transactional
    public Long createNewCafe(CafeCreateRequest request) throws IOException {

//        String cafePhotoUrl = s3Manager.uploadFile(s3Manager.generateCafePhotoKeyName(), request.getCafePhoto());

        Cafe cafe = Cafe.builder()
                .name(request.getName())
                .address(request.getAddress())
//                .photoUrl(cafePhotoUrl)
                .phone(request.getPhone())
                .rating((double)0)
                .reviewCnt(0)
                .operationHours(request.getOperationHours())
                .closeDays(request.getClosedDays())
                .theme(Theme.valueOf(request.getTheme()))
                .build();

        cafeRepository.save(cafe);
        return cafe.getId();
    }



    @Override
    @Transactional
    public void updateCafe(CafeUpdateRequest request) {

        Cafe prev = cafeRepository.findById(request.getCafeId()).get();



        Cafe cafe = Cafe.builder()
                .id(request.getCafeId())
                .name(request.getName())
                .address(request.getAddress())
                .cafeImg(new Image(request.getCafePhotoUrl(),null))
                .phone(request.getPhone())
                .rating(prev.getRating())
                .reviewCnt(prev.getReviewCnt())
                .operationHours(request.getOperationHours())
                .closeDays(request.getClosedDays())
                .theme(Theme.valueOf(request.getTheme()))
                .build();

        cafeRepository.save(cafe);
    }


    @Override
    @Transactional
    public void deleteCafe(CafeDeleteRequest request) {

        cafeRepository.deleteById(request.getCafeId());
    }

}
