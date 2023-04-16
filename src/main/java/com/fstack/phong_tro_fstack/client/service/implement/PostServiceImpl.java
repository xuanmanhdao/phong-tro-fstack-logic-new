package com.fstack.phong_tro_fstack.client.service.implement;

import com.fstack.phong_tro_fstack.base.converter.PostConverter;
import com.fstack.phong_tro_fstack.base.dto.PostDTO;
import com.fstack.phong_tro_fstack.base.entity.PostEntity;
import com.fstack.phong_tro_fstack.client.output.AreaResponse;
import com.fstack.phong_tro_fstack.client.output.RoomResponse;
import com.fstack.phong_tro_fstack.client.output.UserResponse;
import com.fstack.phong_tro_fstack.client.output.post.PagedPostResponse;
import com.fstack.phong_tro_fstack.client.output.post.PostResponse;
import com.fstack.phong_tro_fstack.client.repository.PostRepository;
import com.fstack.phong_tro_fstack.client.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PostServiceImpl implements PostService {

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private PostConverter postConverter;

//    @Override
//    public List<PostDTO> getAllByNumberDateOtherZeroOrderByCreatedAt() {
//        List<PostDTO> result = new ArrayList<>();
////        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "createdAt");
////        Sort sort = Sort.by(order);
//
//        List<PostEntity> postEntities = postRepository.findAllNumberDateOtherZeroOrderByCreatedAt();
//        for (PostEntity postEntity : postEntities) {
//            result.add(postConverter.toDTO(postEntity));
//        }
//        return result;
//    }

  @Override
  public PagedPostResponse getAllByNumberDateOtherZeroOrderByCreatedAt(
      Optional<String> idProvince,
      Optional<String> idDistrict,
      Optional<String> idWard,
      Optional<String> rentPrice,
      Optional<Float> minPrice,
      Optional<Float> maxPrice,
      Optional<String> acreage,
      Optional<Float> minAcreage,
      Optional<Float> maxAcreage,
      Optional<String> exactAddress,
      Optional<Integer> pageNumber,
      Optional<Integer> pageSize
  ) {
    List<Object[]> queryResults = postRepository.getAllPostEnable(idProvince, idDistrict, idWard,
        rentPrice, minPrice, maxPrice, acreage, minAcreage, maxAcreage, exactAddress);
    Map<Long, PostResponse> map = new HashMap<>();

    for (Object[] objects : queryResults) {
      PostResponse postResponse = new PostResponse();
      postResponse.setId((Long) objects[0]);
      postResponse.setThumbnail(objects[1] != null ? objects[1].toString() : "");
      postResponse.setTitle(objects[2].toString());
      postResponse.setContent(objects[3].toString());
      postResponse.setCreatedAt((Date) objects[4]);
      postResponse.setUpdatedAt((Date) objects[5]);
      postResponse.setNumberDate((Integer) objects[6]);
      postResponse.setPhoneNumber((String) objects[7]);
      postResponse.setPhoneZalo((String) objects[8]);
      postResponse.setStatus((Integer) objects[9]);
      postResponse.setUserResponse(new UserResponse((Long) objects[10], objects[11].toString()));

      AreaResponse areaResponse = new AreaResponse();
      areaResponse.setId((Long) objects[12]);
      areaResponse.setExactAddress(objects[13].toString());
      areaResponse.setLatitude(objects[14] != null ? objects[14].toString() : "");
      areaResponse.setLongitude(objects[15] != null ? objects[15].toString() : "");
      areaResponse.setName(objects[16] != null ? objects[16].toString() : "");
      areaResponse.setProvinceName(objects[27].toString());
      areaResponse.setDistrictName(objects[29].toString());
      areaResponse.setWardName(objects[31].toString());

      Long idPost = (Long) objects[0];
      RoomResponse roomResponse = new RoomResponse();
      roomResponse.setId((Long) objects[17]);
      roomResponse.setName(objects[18].toString());
      roomResponse.setDescription(objects[19].toString());
      roomResponse.setAcreage((Float) objects[20]);
      roomResponse.setRentPrice((Float) objects[21]);
      roomResponse.setElectricService((Float) objects[22]);
      roomResponse.setWaterService((Float) objects[23]);
      roomResponse.setImage(objects[24].toString());
      roomResponse.setVideo(objects[25].toString());

      if (!map.containsKey(idPost)) {
        List<RoomResponse> roomResponses = new ArrayList<>();
        roomResponses.add(roomResponse);
        areaResponse.setRoomResponses(roomResponses);
        postResponse.setAreaResponse(areaResponse);
        map.put(idPost, postResponse);
      } else {
        map.get(idPost).getAreaResponse().getRoomResponses().add(roomResponse);
      }

    }

    List<PostResponse> result = new ArrayList<>(map.values());

    Collections.sort(result, new Comparator<PostResponse>() {
      @Override
      public int compare(PostResponse o1, PostResponse o2) {
        return o2.getCreatedAt().compareTo(o1.getCreatedAt());
      }
    });

    int totalRecordResult = result.size();
    int pageNumberRequest = pageNumber.orElse(1);// số trang cần lấy
    int pageSizeRequest = pageSize.orElse(9);// kích thước trang
    int totalPage = (int) Math.ceil((double) totalRecordResult / pageSizeRequest);
    int startIndex = (pageNumberRequest - 1) * pageSizeRequest;
    int endIndex = Math.min(startIndex + pageSizeRequest, totalRecordResult);

    StringBuilder searchRequestBuilder = new StringBuilder();
    String separator = "&";
    if (idProvince.isPresent()) {
      searchRequestBuilder.append("idProvince=").append(idProvince.get()).append(separator);
    }

    if (idDistrict.isPresent()) {
      searchRequestBuilder.append("idDistrict=").append(idDistrict.get()).append(separator);
    }

    if (idWard.isPresent()) {
      searchRequestBuilder.append("idWard=").append(idWard.get()).append(separator);
    }

    if (rentPrice.isPresent()) {
      searchRequestBuilder.append("rentPrice=").append(rentPrice.get()).append(separator);
    }

    if (minPrice.isPresent()) {
      searchRequestBuilder.append("minPrice=").append(minPrice.get()).append(separator);
    }

    if (maxPrice.isPresent()) {
      searchRequestBuilder.append("maxPrice=").append(maxPrice.get()).append(separator);
    }

    if (acreage.isPresent()) {
      searchRequestBuilder.append("acreage=").append(acreage.get()).append(separator);
    }

    if (minAcreage.isPresent()) {
      searchRequestBuilder.append("minAcreage=").append(minAcreage.get()).append(separator);
    }

    if (maxAcreage.isPresent()) {
      searchRequestBuilder.append("maxAcreage=").append(maxAcreage.get()).append(separator);
    }

    if (exactAddress.isPresent()) {
      searchRequestBuilder.append("exactAddress=").append(exactAddress.get()).append(separator);
    }

//    if (pageNumber.isPresent()) {
//      searchRequestBuilder.append("pageNumber=").append(pageNumber.get()).append(separator);
//    }
//
//    if (pageSize.isPresent()) {
//      searchRequestBuilder.append("pageSize=").append(pageSize.get()).append(separator);
//    }

// remove the last separator if it exists
    if (searchRequestBuilder.length() > 0
        && searchRequestBuilder.charAt(searchRequestBuilder.length() - 1) == '&') {
      searchRequestBuilder.deleteCharAt(searchRequestBuilder.length() - 1);
    }

    String searchRequest = searchRequestBuilder.toString();

    PagedPostResponse resultResponse = new PagedPostResponse();
    resultResponse.setPostResponses(result.subList(startIndex, endIndex));
    resultResponse.setTotalPage(totalPage);
    resultResponse.setPageNumber(pageNumberRequest);
    resultResponse.setPageSize(pageSizeRequest);
    resultResponse.setSearchRequest(searchRequest);

    return resultResponse;
  }

  @Override
  public PostResponse getDetailPost(Long id) {
    List<Object[]> queryResults = postRepository.getDetailPost(id);
    Map<Long, PostResponse> map = new HashMap<>();

    for (Object[] objects : queryResults) {
      PostResponse postResponse = new PostResponse();
      postResponse.setId((Long) objects[0]);
      postResponse.setThumbnail(objects[1] != null ? objects[1].toString() : "");
      postResponse.setTitle(objects[2].toString());
      postResponse.setContent(objects[3].toString());
      postResponse.setCreatedAt((Date) objects[4]);
      postResponse.setUpdatedAt((Date) objects[5]);
      postResponse.setNumberDate((Integer) objects[6]);
      postResponse.setPhoneNumber((String) objects[7]);
      postResponse.setPhoneZalo((String) objects[8]);
      postResponse.setStatus((Integer) objects[9]);
      postResponse.setUserResponse(new UserResponse((Long) objects[10], objects[11].toString()));

      AreaResponse areaResponse = new AreaResponse();
      areaResponse.setId((Long) objects[12]);
      areaResponse.setExactAddress(objects[13].toString());
      areaResponse.setLatitude(objects[14] != null ? objects[14].toString() : "");
      areaResponse.setLongitude(objects[15] != null ? objects[15].toString() : "");
      areaResponse.setName(objects[16] != null ? objects[16].toString() : "");
      areaResponse.setProvinceName(objects[27].toString());
      areaResponse.setDistrictName(objects[29].toString());
      areaResponse.setWardName(objects[31].toString());

      Long idPost = (Long) objects[0];
      RoomResponse roomResponse = new RoomResponse();
      roomResponse.setId((Long) objects[17]);
      roomResponse.setName(objects[18].toString());
      roomResponse.setDescription(objects[19].toString());
      roomResponse.setAcreage((Float) objects[20]);
      roomResponse.setRentPrice((Float) objects[21]);
      roomResponse.setElectricService((Float) objects[22]);
      roomResponse.setWaterService((Float) objects[23]);
      roomResponse.setImage(objects[24].toString());
      roomResponse.setVideo(objects[25].toString());

      if (!map.containsKey(idPost)) {
        List<RoomResponse> roomResponses = new ArrayList<>();
        roomResponses.add(roomResponse);
        areaResponse.setRoomResponses(roomResponses);
        postResponse.setAreaResponse(areaResponse);
        map.put(idPost, postResponse);
      } else {
        map.get(idPost).getAreaResponse().getRoomResponses().add(roomResponse);
      }

    }

    List<PostResponse> resultTmp = new ArrayList<>(map.values());

    PostResponse result = resultTmp.get(0);
    return result;
  }


}
