DROP TABLE `user` IF EXISTS;

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `first_name` varchar(191) NOT NULL,
  `last_name` varchar(191) NOT NULL,
  `username` varchar(191) NOT NULL,
  `email` varchar(191) NOT NULL,
  `password` varchar(191) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL
);

DROP TABLE `review_rating` IF EXISTS;

CREATE TABLE `review_rating` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `review` longtext NOT NULL,
  `rating` int(11) NOT NULL
);

DROP TABLE `items` IF EXISTS;

CREATE TABLE `items` (
  `id` int(11) NOT NULL,
  `name` varchar(191) NOT NULL,
  `type` varchar(191) NOT NULL
);

DROP TABLE `item_casts` IF EXISTS;

CREATE TABLE `item_casts` (
  `id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `cast` varchar(191) NOT NULL,
  `role` varchar(191) NOT NULL
);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `item_casts`
--
ALTER TABLE `item_casts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `review_rating`
--
ALTER TABLE `review_rating`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `item_casts`
--
ALTER TABLE `item_casts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `review_rating`
--
ALTER TABLE `review_rating`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `item_casts`
--
ALTER TABLE `item_casts`
  ADD CONSTRAINT `item_casts_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `review_rating`
--
ALTER TABLE `review_rating`
  ADD CONSTRAINT `review_rating_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`);

ALTER TABLE `review_rating`
  ADD CONSTRAINT `review_rating_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;
