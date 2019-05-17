##PetController 宠物业务
###publishOrUpdate发布
```
POST/pet/publishOrUpdate
```
参数
```$xslt

body:{
    petImages：MultipartFile[] , //图片
    petType：'1' , //1:托养，2：交易，3：相亲，4：寻宠
    petPrice;'1'， //价钱
    userId;'1111', //用户id
    petTitle: '1'，  //标题
    petDesc：'1',  //描述
    petCity;'1',  //城市
    wx:'1',   //微信
    phone:'1', //电话
    petName:'1',  //宠物昵称
    petAge:'1',   //宠物年龄
    petGender:'1',//性别 1:男 2:女
    petBreed:'1',  //品种
    isVaccine:'1'   //是否接受疫苗 1:是 0:否
}
```
返回
```
success:{
    "status": 0,
    "msg": "添加成功"
}
error:{
    "status": 1,
    "msg": "添加失败"
}
```
###petDetail查看详情
```
GET/pet/petDetail
```
参数
```$xslt

body:{
   petId:1   //id
}
```
返回
```
success:
error:
```