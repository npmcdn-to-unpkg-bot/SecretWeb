   select
        article0_.id as id1_3_,
        article0_.creation_time as creation2_3_,
        article0_.modification_time as modifica3_3_,
        article0_.content as content4_3_,
        article0_.image_url as image_ur5_3_,
        article0_.writer_id as writer_i6_3_ 
    from
        da_article article0_ 
    where
        article0_.writer_id in (
            select
                account1_.id 
            from
                cm_account account1_ 
            where
                account1_.id='2c980ec9568ff26e01568ff344100000'
        ) 
        or article0_.writer_id in (
            select
                friend2_.acceptant_id 
            from
                cm_friend friend2_ 
            where
                requester_id='2c980ec9568ff26e01568ff344100000' 
                and friend2_.accepted=true
        ) 
    order by
        article0_.id desc