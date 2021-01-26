import * as React from 'react';

import { useTranslation } from 'react-i18next';

import { Grid } from '@material-ui/core';
import IconRefresh from '@material-ui/icons/Refresh';
import IconPlay from '@material-ui/icons/PlayArrow';
import IconPause from '@material-ui/icons/Pause';

import { IconButton } from '@centreon/ui';

import {
  labelRefresh,
  labelDisableAutorefresh,
  labelEnableAutorefresh,
} from '../../translatedLabels';
import { ResourceContext, useResourceContext } from '../../Context';
import memoizeComponent from '../../memoizedComponent';

interface AutorefreshProps {
  enabledAutorefresh: boolean;
  toggleAutorefresh: () => void;
}

const AutorefreshButton = ({
  enabledAutorefresh,
  toggleAutorefresh,
}: AutorefreshProps): JSX.Element => {
  const { t } = useTranslation();

  const label = enabledAutorefresh
    ? labelDisableAutorefresh
    : labelEnableAutorefresh;

  return (
    <IconButton
      ariaLabel={t(label)}
      title={t(label)}
      onClick={toggleAutorefresh}
      size="small"
    >
      {enabledAutorefresh ? <IconPause /> : <IconPlay />}
    </IconButton>
  );
};

interface Props {
  onRefresh: () => void;
}

type ResourceContextProps = Pick<
  ResourceContext,
  'enabledAutorefresh' | 'setEnabledAutorefresh' | 'sending'
>;

const RefreshActionsContent = ({
  onRefresh,
  enabledAutorefresh,
  setEnabledAutorefresh,
  sending,
}: Props & ResourceContextProps): JSX.Element => {
  const { t } = useTranslation();

  const toggleAutorefresh = (): void => {
    setEnabledAutorefresh(!enabledAutorefresh);
  };

  return (
    <Grid container spacing={1}>
      <Grid item>
        <IconButton
          title={t(labelRefresh)}
          ariaLabel={t(labelRefresh)}
          disabled={sending}
          onClick={onRefresh}
          size="small"
        >
          <IconRefresh />
        </IconButton>
      </Grid>
      <Grid item>
        <AutorefreshButton
          enabledAutorefresh={enabledAutorefresh}
          toggleAutorefresh={toggleAutorefresh}
        />
      </Grid>
    </Grid>
  );
};

const MemoizedRefreshActionsContent = memoizeComponent<
  Props & ResourceContextProps
>({
  memoProps: ['sending', 'enabledAutorefresh'],
  Component: RefreshActionsContent,
});

const RefreshActions = ({ onRefresh }: Props): JSX.Element => {
  const {
    enabledAutorefresh,
    setEnabledAutorefresh,
    sending,
  } = useResourceContext();

  return (
    <MemoizedRefreshActionsContent
      onRefresh={onRefresh}
      enabledAutorefresh={enabledAutorefresh}
      setEnabledAutorefresh={setEnabledAutorefresh}
      sending={sending}
    />
  );
};

export default RefreshActions;
