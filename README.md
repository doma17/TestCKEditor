# CKEditor Test Repository


CKEditor는 JavaScript로 작성된 텍스트 에디터입니다.


CKEditor의 기본적인 기능과 글 작성/수정, 글 목록에 대한 기능을 사용했습니다.

CKEditor상에서 이미지를 업로드시 AWS S3 버킷에 업로드 되도록 설정되어있습니다.

Title, Text 데이터 경우에는 AWS RDS에서 MariaDB를 사용했습니다.

---
- Image Server : AWS S3
- DB : AWS RDS (MariaDB)
- View Template : Mustache
