---
layout: post
title: "计算广告-笔记"
date: 2021-05-23
categories: Business
author: linliangqi
---

> [计算广告](https://book.douban.com/subject/34804492/)

- [一、在线广告市场与背景](#一在线广告市场与背景)
- [二、计算广告基础](#二计算广告基础)

---

### 一、在线广告市场与背景
* 产品本身没有利润，在免费产品传播信息的过程中，获得了三项可变现的**核心资产**：
    + **流量** 在产品本身功能之外夹带一些付费内容，例如广告
    + **数据** 用户在使用产品过程中留下一些行为和属性，可以基于这些信息调整付费内容的策略以提高效率
    + **影响力** 指产品或内容获得了高于普通水准的关注和信任。

    将这三项资产通过商业产品的形式转变为收入的过程，就是**商业化（monetization）**。
    
    **e.g.** 公众号软文、网红带货属于影响力变现，平时浏览新闻或其他产品时夹带在产品内容中的广告属于流量和数据变现。

* **计算广告（computational advertising）** 的特点：
    + **行为数据**规模巨大，但对一致性要求不高
    + 随着数据采样率的降低，解决问题的收益快速下降。这是典型的大数据问题，这意味着要对数据进行**全量加工**。对于计算广告，需要对每一个人的行为做定制化推送，而无法只采样一部分人做处理。
    + **自动化应用**，即将数据处理的结果直接送给对业务进行自动决策的引擎。从用户行为数据的收集，到受众定向，再到线上根据用户标签的自动决策，整个过程都是自动进行的。

* “广告的根本目的是广告主通过媒体达到低成本的用户接触。” 为了确定是否成本较低，需要用到**投入产出比（Return on Investment, ROI）** 这一评价指标，即某次广告活动的总产出和总投入的比例。
  
* 对于互联网广告，有：“一切付费的信息、产品或服务的传播渠道，都是广告。”
  
* 在广告业务中，数据变现是附着在流量变现的基础上的。

* **定向广告** 对计算技术提出两个要求：
  + 受众定向：通过技术手段标定某个用户的信息
  + 广告投放：广告投送页面为实时响应前端请求，并动态决策和返回合适的广告创意
  
  此时定向广告以合约形式进行：媒体与广告主约定广告位、时间段和投放量，并在此基础上确定合同的总金额以及量未达标的情况下的赔偿方案。这种交易方式称为**担保式投送（Guaranteed Delivery，GD）**，主要面向品牌广告主，并且按照**按千次展示付费（Cost per Mille, CPM）** 的计费方式。

* **竞价广告** 供给方只向广告主保证单位流量的成本，不再给出量的保证。基于竞价和精准人群定向这两个核心功能，产生了广告网络（AD Network，ADN）这种新的产品形式，它的结算以**按点击付费（Cost per Click, CPC）** 的方式为主。

* **实时竞价（Real Time Bidding，RTB）**：需求方按自己的人群定义来挑选流量，它将竞价过程从广告主预先出价变成每次展示时实时出价。由此催生出实时竞价方式进行变现的新产品形态——**广告交易平台（AD Exchange，ADX）**，通过实时竞价，按照定制化人群标签采买广告，这样的产品就是**需求方平台（Demand Side Platform，DSP）**。

---

### 二、计算广告基础