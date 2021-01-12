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

namespace Centreon\Domain\HostConfiguration\Model;

use Centreon\Domain\Common\Assertion\Assertion;
use Centreon\Domain\Media\Model\Image;

/**
 * This class is designed to represent a host severity.
 *
 * @package Centreon\Domain\HostConfiguration\Model
 */
class HostSeverity
{
    public const MAX_NAME_LENGTH = 200,
        MAX_ALIAS_LENGTH = 200,
        MAX_COMMENTS_LENGTH = 65535;

    /**
     * @var int|null
     */
    private $id;

    /**
     * @var string Define a short name for this severity. It will be displayed with this name in the ACL configuration.
     */
    private $name;

    /**
     * @var string Longer description of this severity.
     */
    private $alias;

    /**
     * @var int Priority.
     */
    private $level;

    /**
     * @var Image Define the image that should be associated with this severity.
     */
    private $icon;

    /**
     * @var string|null Comments regarding this severity.
     */
    private $comments;

    /**
     * @var bool Indicates whether this host severity is enabled or not (TRUE by default)
     */
    private $isActivated = true;

    /**
     * @return int|null
     */
    public function getId(): ?int
    {
        return $this->id;
    }

    /**
     * @param int $id
     * @return HostSeverity
     */
    public function setId(int $id): HostSeverity
    {
        $this->id = $id;
        return $this;
    }

    /**
     * @return string
     */
    public function getName(): string
    {
        return $this->name;
    }

    /**
     * @param string $name
     * @return HostSeverity
     * @throws \Assert\AssertionFailedException
     */
    public function setName(string $name): HostSeverity
    {
        if ($name !== null) {
            Assertion::maxLength($name, self::MAX_NAME_LENGTH, 'HostSeverity::name');
        }
        $this->name = $name;
        return $this;
    }

    /**
     * @return string
     */
    public function getAlias(): string
    {
        return $this->alias;
    }

    /**
     * @param string $alias
     * @return HostSeverity
     * @throws \Assert\AssertionFailedException
     */
    public function setAlias(string $alias): HostSeverity
    {
        if ($alias !== null) {
            Assertion::maxLength($alias, self::MAX_ALIAS_LENGTH, 'HostSeverity::alias');
        }
        $this->alias = $alias;
        return $this;
    }

    /**
     * @return bool
     */
    public function isActivated(): bool
    {
        return $this->isActivated;
    }

    /**
     * @param bool $isActivated
     * @return HostSeverity
     */
    public function setIsActivated(bool $isActivated): HostSeverity
    {
        $this->isActivated = $isActivated;
        return $this;
    }

    /**
     * @return int
     */
    public function getLevel(): int
    {
        return $this->level;
    }

    /**
     * @param int $level
     * @return $this
     */
    public function setLevel(int $level): HostSeverity
    {
        $this->level = $level;
        return $this;
    }

    /**
     * @return Image
     */
    public function getIcon(): Image
    {
        return $this->icon;
    }

    /**
     * @param Image $icon
     * @return $this
     */
    public function setIcon(Image $icon): HostSeverity
    {
        $this->icon = $icon;
        return $this;
    }

    /**
     * @return string|null
     */
    public function getComments(): ?string
    {
        return $this->comments;
    }

    /**
     * @param string|null $comments
     * @return HostSeverity
     * @throws \Assert\AssertionFailedException
     */
    public function setComments(?string $comments): HostSeverity
    {
        if ($comments !== null) {
            Assertion::maxLength($comments, self::MAX_COMMENTS_LENGTH, 'HostSeverity::comments');
        }
        $this->comments = $comments;
        return $this;
    }
}