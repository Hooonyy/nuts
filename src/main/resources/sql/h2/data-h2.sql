INSERT INTO users (users_seq, users_id, password, users_name, address, age, file_path) VALUES
    (1, 'main', '33275a8aa48ea918bd53a9181aa975f15ab0d0645398f5918a006d08675c1cb27d5c645dbd084eee56e675e25ba4019f2ecea37ca9e2995b49fcb12c096a032e', 'hi', '서울시', 'A47b8frJhO8ZzRJmRBCl6g==', '\images\user\moon.jpg
'),
    (2, 'dia', 'a8cebf1698dc14282c507b1e1cfb7f2c9d5216aa7bd0854b50561e02c2b99d9a38945ec0f81e55f9699062b1eac6d0083411c839ba2b27c6a15b494463bc5c73', 'tyna', '경기도', '0y83Z5JBNRgn0eHsuW/Dvg==', ''),
    (3, 'gold', '2f116a908cf26341547be5d4eec5d9e325fa75f1b6bfd6ba1618d9283b9aeb60cfb00a6a8508e0bcff4e673a52abf31cad6d7b26ba3994c087a0566ead3b2330', 'symon', '분당구', 'Z1GkAOc/ef3wUnPBl1BFYg==', ''),
    (4, 'silver', 'c3e47544d233ab0c129605b325a5edfd8ad0a2b58e2ea910ffef872876139407c578d06ff4b9400332d0c438f5dcba9ff5ecbca372167322c73da291c1cef670', 'epic', '대전', 'MoRFvl6AU5jT1msKMQQ6Pw==', ''),
    (5, 'red', 'b1db8b683bf3be35360e35d80db84b139d87aa3ffc21ac9ddb60af95fe0c694a30a3ae6b2468c94c3223c13fda8f8b45e2085cde506c51569af3257f5657bc27', 'green', '제주도', 'qLQ5KmHjM/PNIc0lahVivg==', ''),
    (6, 'json', '0549ab26aefcba2df2f37dc4a2820597b0142f86ac8984214951c44621b63d1696f03cc3093ba1c7c88a7259d5cc83e7396e1614d1a5f3c3c0ad96e70cdfd0d7', 'languege', '독도', '4N7NjlPwdRRFd7+BQjn9YQ==', ''),
    (7, 'tuple', '498ad5123733c1e8fdc55bae5febc47aca3ea726c9408f4987db5c5b196258bc573c76498fbd5be09a137062f92ea2423f69a3aaaf5ffd3b80b1e96df4f96d17', 'tutle', '독도', '7xf7HJNU3DSH9Gceb14hZA==', ''),
    (8, 'apple', '3e720266b7b7d8084466a12ec088fbfe1a5361a35619cd73603c1f935b153eebd7233249e1b50be05d56b95a78fafacbf8fb45b9b8d6d144932ab00754073486', 'fruit', '독도', 'LbLgRpbUfpblUaAnD6FAIA==', ''),
    (9, 'bee', 'b61265c6a561e90476ef78be3d2cea768306afa55a2e510c591008f8dc3e6f8a9520687e5fd3805e2da67cf5c58aa1603f960e027b0d5307fd7f98b673dd172f', 'insect', '독도', '/zvXT5Dh0NIk29I7io5xTQ==', ''),
    (10, 'typon', 'c6001d5b2ac3df314204a8f9d7a00e1503c9aba0fd4538645de4bf4cc7e2555cfe9ff9d0236bf327ed3e907849a98df4d330c4bea551017d465b4c1d9b80bcb0', 'taepoong', '독도', '/QkN11+ugb0NsEXbIYW2Ow==', ''),
    (11, 'salley', 'd404559f602eab6fd602ac7680dacbfaadd13630335e951f097af3900e9de176b6db28512f2e000b9d04fba5133e8b1c6e8df59db3a8ab9d60be4b97cc9e81db', 'sllye', '독도', 'FUj5rY+I2Wu96NID8rC1nA==', ''),
    (12, 'hoxy', '99c552a2f9d880b40e3ec981c995f1a679b3b734827003363643ad025f4c8ed395227d0d361e798a6c558711ec1533f3f9bb87594efe6453b23c35b801014f91', 'nothing', '독도', 'mehQaGidtXR2FY2/Sjv0Aw==', ''),
    (13, 'onetwo', 'ceb733929efdb521551002f6ab704a9bfb6b2b3547aaee86a1ee19d9c093187c8ca069b5bbf56f529406a84946eded3027d411cc958e1cffab930f07f1c92005', '12', '독도', '/zvXT5Dh0NIk29I7io5xTQ==', ''),
    (14, 'boxer', 'afb76e6c9d03dc508a093f07dab123306b724df121cae29bcbd58dd901c2a07fde980f5f1376affe6962e1338349957af09ad889ef651ee8629ac75617054681', 'kkkk', '독도', 'A47b8frJhO8ZzRJmRBCl6g==', ''),
    (15, 'xenon', '72cd06bd36df7019dbe93283eddc1774d205d2a52e247000683d69d9f47c56ac65fa5538404829518342c75d7f3175936df906fdafd3e5089c4c1e2f698b4a5a', 'axon', '독도', 'AV8DrCqqVuYYvtWdLvZsYA==', ''),
    (16, 'moo', '2801cd8d45673bab0905b13467cfff3777ec402b5b1f047f8e41e01e556f2643ebeb3d73625737b25b6ff63b73a9478e1a65fd73f7db4b900ff44644ef762114', 'cow', '독도', 'W3o1DZ2Hpbwy0wTMpmWQ6Q==', '');

INSERT INTO users_address (users_address_seq, users_seq, address1, address2, nickname, is_main_address) VALUES
            (1, '1', '1111', '1111', 'nick1', 'Y'),
            (2, '1', '111', '2222', 'nick2', 'N'),
            (3, '1', '1111', '3333', 'nick3', 'N'),
            (4, '2', '2222', '11111', 'nick1', 'N'),
            (5, '2', '22222', '22222', 'nick2', 'Y'),
            (6, '3', '3333', '11111', 'nick1', 'Y'),
            (7, '4', '44444', '11111', 'nick1', 'Y'),
            (8, '5', '555555', '1111', 'nick1', 'Y'),
            (9, '6', '6666', '11111', 'nick1', 'Y');

INSERT INTO product (product_seq, product_name, product_imgpath, product_price, product_contents, product_etc, product_discount_code, product_discount, product_isdel) VALUES
           (1, 'apple', '\images\product\apple1.jpg', 1300, '맛있는 사과', '기타', 'percent', 12, 'N'),
           (2, 'banana', '\images\product\banana.jpg', 3300, '길쭉한 바나나', '기타', 'price', 250, 'N'),
           (3, 'carrot', '\images\product\carrot.jpg', 800, '향긋한 당근', '기타', 'none', 9, 'N'),
           (4, 'pineapple', '\images\product\pineapple.jpg', 6700, '파인~애플', '기타', 'percent', 16, 'N'),
           (5, 'melon', '\images\product\melon.jpg', 13700, '달콤한 메론', '기타', 'none', 9, 'N'),
           (6, 'cherry', '\images\product\cherry.jpg', 12600, '상큼한 체리', '기타', 'percent', 22, 'N'),
           (7, 'mango', '\images\product\mango.jpg', 9900, '망고 망고', '기타', 'price', 18, 'N'),
           (8, 'yuja', '\images\product\yuja.jpg', 1500, '유자차로 제격 유자', '기타', 'price', 500, 'N'),
           (9, 'honey', '\images\product\honey.jpg', 5600, '아카시아 꿀', '기타', 'none', 9, 'N'),
           (10, 'rose', '\images\product\rose.jpg', 1950, '새빨간 로즈', '기타', 'percent', 4, 'N');