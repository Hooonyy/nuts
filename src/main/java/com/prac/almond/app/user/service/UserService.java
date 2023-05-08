package com.prac.almond.app.user.service;

import com.prac.almond.app.user.mapper.UserMapper;
import com.prac.almond.app.user.model.User;
import com.prac.almond.app.user.model.UserDto;
import com.prac.almond.app.user.model.UserPaging;
import com.prac.almond.app.user.model.UserParam;
import com.prac.almond.app.util.Aes256;
import com.prac.almond.app.util.Sha512;
import com.prac.almond.app.util.UploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final Sha512 sha512;


    public String saveUserData(UserDto userDto) throws Exception {
        int checkUserId = userMapper.checkUserId(userDto.getUsersId());
        System.out.println(checkUserId);
        String str = "success";
        String pattern = "^[a-zA-Z0-9]{4,20}$";


        if (checkUserId == 0) {
            //정규식 체크 한글x 특수문자x 8자이상 20자이하
            if (Pattern.matches(pattern, userDto.getUsersId())) {
                //통과
                //비밀번호 단방향 암호화
                userDto.setPassword(sha512.encSha256(userDto.getPassword()));
                //나이 양방향 암호화
                userDto.setAgeEncrypt(Aes256.encrypt(userDto.getAge()));

            } else {
                System.out.println("해당id는 사용할수없습니다(한글x 특수문자x 4자이상 20자이하).");
                str = "\"해당id는 사용할수없습니다(한글x 특수문자x 20자이하).\"";
            }
            if (userDto.getMultipartFile() != null) {
                try {
                    String filePath = UploadUtil.uploadImage("user", userDto.getMultipartFile());
                    userDto.setFilePath(filePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("fail");
            str = "fail";
        }
        int saveUserData = userMapper.saveUserData(userDto);
        return str;
    }

    public UserPaging findUserPaging(UserParam userParam) throws Exception {

        //조회
        List<User> list = userMapper.findUserList(userParam);

        for (int a = 0; a < list.size(); a++) {
            list.get(a).setAge(Aes256.decrypt(list.get(a).getAge()));
        }
        UserPaging userPaging = new UserPaging();
        userPaging.setUserList(list);
        //전체 count
        int countUserList = userMapper.countUserList(userParam);
        userPaging.setListSize(countUserList);
        userPaging.setPage(userParam.getPageNum());

        return userPaging;
    }

    public User findUserInfo(Integer usersSeq) throws Exception {
        User userInfoData = userMapper.findUserInfo(usersSeq);
        userInfoData.setAge(Aes256.decrypt(userInfoData.getAge()));
        return userInfoData;
    }

    public String updateUserData(UserDto userDto) throws Exception {
        User user = new User();
        user.setUsersSeq(userDto.getUsersSeq());
        user.setUsersName(userDto.getUsersName());
        user.setAge(Aes256.encrypt(userDto.getAge()));
        user.setAddress(userDto.getAddress());
        user.setFilePath(userDto.getFilePath());


        if (userDto.getMultipartFile() != null) {
            try {
                String filePath = UploadUtil.uploadImage("user", userDto.getMultipartFile());
                user.setFileUploadYn("Y");
                user.setFilePath(filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            user.setFileUploadYn("N");
        }
        int updateUserData = userMapper.updateUserData(user);
        if (updateUserData == 1) {

            return "success";
        }
        return "fail";

    }

    public String deleteUserData(int usersSeq) {
        userMapper.deleteUserData(usersSeq);
        return "success";
    }
}
