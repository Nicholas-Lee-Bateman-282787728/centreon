<?php

/*
 * Copyright 2005 - 2020 Centreon (https://www.centreon.com/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * For more information : contact@centreon.com
 *
 */
declare(strict_types=1);

namespace Centreon\Infrastructure\HostConfiguration\Repository\Model;

use Centreon\Domain\HostConfiguration\Model\HostSeverity;

/**
 * This class is designed to provide a way to create the HostSeverity entity from the database.
 *
 * @package Centreon\Infrastructure\HostConfiguration\Repository\Model
 */
class HostSeverityFactoryRdb
{
    /**
     * Create a HostSeverity entity from database data.
     *
     * @param array<string, mixed> $data
     * @return HostSeverity
     * @throws \Assert\AssertionFailedException
     */
    public static function create(array $data): HostSeverity
    {
        $hostSeverity = (new HostSeverity())
            ->setId((int) $data['hc_id'])
            ->setName($data['hc_name'])
            ->setAlias($data['hc_alias'])
            ->setIsActivated($data['hc_activate'] === '1');
        if ($data['hc_comment'] !== null) {
            $hostSeverity->setComments($data['hc_comment']);
        }
        return $hostSeverity;
    }
}