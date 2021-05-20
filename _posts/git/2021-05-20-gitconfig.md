---
layout: post
title: "Git 配置"
date: 2021-05-20
categories: Git
author: Maxwell-L
---

### 配置用户、邮箱
```
global config --global user.name your_username
global config --global user.email "your_email"
```

### 生成密钥
```
ssh-keygen -o
```

### 查看公钥
```
cat ~/.ssh/id_rsa.pub
```