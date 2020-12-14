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

namespace Centreon\Domain\HostConfiguration\Exception;

/**
 * This class is designed to contain all exceptions for the context of the host template.
 *
 * @package Centreon\Domain\HostConfiguration\Exception
 */
class HostGroupException extends \Exception
{
    public static function searchHostGroupsException(\Exception $ex): self
    {
        return new self(_('Error when searching for host groups'), 0, $ex);
    }
    
    public static function countHostGroupsException(\Exception $ex): self
    {
        return new self(_('Error while searching for the number of host group'), 0, $ex);
    }
    
    public static function searchUsedHostGroupsNameException(\Exception $ex): self
    {
        return new self(_('Error when searching for already used host group names'), 0, $ex);
    }
}
